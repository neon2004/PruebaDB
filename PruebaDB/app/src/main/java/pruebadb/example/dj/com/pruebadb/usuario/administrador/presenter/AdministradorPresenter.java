package pruebadb.example.dj.com.pruebadb.usuario.administrador.presenter;

import android.database.Cursor;
import android.util.Log;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import pruebadb.example.dj.com.pruebadb.R;
import pruebadb.example.dj.com.pruebadb.common.Adapter.AdaptadorDataService;
import pruebadb.example.dj.com.pruebadb.common.Adapter.AdaptadorDataServiceBD;
import pruebadb.example.dj.com.pruebadb.common.base.BaseApplication;
import pruebadb.example.dj.com.pruebadb.common.rest.Fruit;
import pruebadb.example.dj.com.pruebadb.common.util.UsuarioType;
import pruebadb.example.dj.com.pruebadb.common.util.Util;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.DataService;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.PermisoTareasUser;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.PermisoTareasUserDao;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.RelacionUsuarioTarea;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.RelacionUsuarioTareaDao;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Tarea;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.TareaDao;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Usuario;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.UsuarioDao;
import pruebadb.example.dj.com.pruebadb.usuario.administrador.contract.AdministradorContract;
import pruebadb.example.dj.com.pruebadb.usuario.administrador.view.AdministradorFragment;


/**
 * Created by neon2004 on 30/04/2017.
 */

public class AdministradorPresenter implements AdministradorContract.Presenter{
    private AdministradorContract.View adminView;
    private AdaptadorDataServiceBD adaptadorBD;
    private AdaptadorDataService adaptador;


    public AdministradorPresenter(AdministradorFragment administradorFragment) {
        adminView = administradorFragment;
        final List<Usuario> usuarios = BaseApplication.getInstance().getUserDao().queryBuilder()
                .where(UsuarioDao.Properties.TipoUsuario.notEq(UsuarioType.Administrador)).list();
        adminView.setAdapterSpinnerList(usuarios);
    }

    @Override
    public void crearTarea(String descripcion, int duracion, ArrayList<Long> idUsers) {
        Tarea tareaIdMax = BaseApplication.getInstance().getTareaDao().queryBuilder().limit(1).orderDesc(TareaDao.Properties.Tarea_id).unique();
        Tarea tarea = BaseApplication.getInstance().getTareaDao().queryBuilder().where(TareaDao.Properties.Descripcion.eq(descripcion)).unique();

        List<Tarea> l = BaseApplication.getInstance().getTareaDao().queryBuilder().list();

        if(tarea == null){
            tarea = new Tarea();
            tarea.setDescripcion(descripcion);
            tarea.setDuracion(duracion);

            int id = Integer.parseInt(tareaIdMax.getTarea_id())+1;
            tarea.setTarea_id(String.valueOf(id));
        }else{
            tarea.setDuracion(duracion);
        }

        long insertado = BaseApplication.getInstance().getTareaDao().insertOrReplace(tarea);
        List<Tarea> l1 = BaseApplication.getInstance().getTareaDao().queryBuilder().list();

        if(insertado > 0){
            addPermisoUser(idUsers,tarea);
            asignarTareaUser(tarea);
        }else{
            adminView.showMsgResult(insertado);
        }
    }

    @Override
    public void createAdapter(ArrayList<Fruit> listFrutas) {
        adaptador = new AdaptadorDataService(listFrutas);
        adminView.setAdapterReciclerView(adaptador);
    }

    @Override
    public void createAdapterBD(List<DataService> listDataService) {
        adaptadorBD = new AdaptadorDataServiceBD(listDataService);
        adminView.setAdapterReciclerViewBD(adaptadorBD);
    }

    private void addPermisoUser(ArrayList<Long> idUsers, Tarea tarea) {
        for (Long item:idUsers ) {
            PermisoTareasUser permisoAsignados = buscarPermisoTarea(item, tarea);

            List<PermisoTareasUser> l = BaseApplication.getInstance().getPermisoTareasUserDao().queryBuilder().list();


            if(permisoAsignados == null){
                PermisoTareasUser permiso = new PermisoTareasUser();
                permiso.setIdTarea(tarea.getId());
                permiso.setIdUser(item);
                permiso.setPermiso(true);
                BaseApplication.getInstance().getPermisoTareasUserDao().insertInTx(permiso);
            }

            List<PermisoTareasUser> l1 = BaseApplication.getInstance().getPermisoTareasUserDao().queryBuilder().list();

            Log.d("b","b");
        }

    }

    private void asignarTareaUser(Tarea tarea) {


        List<RelacionUsuarioTarea> l = BaseApplication.getInstance().getRelacionUserTareaDao().queryBuilder().list();

        Integer count = 0;
        Long idUser = null;
        ArrayList<Long> listIdUser = new  ArrayList<Long> ();
        PermisoTareasUser permisoAsignados = null;

        Cursor cursor1 = Util.getUsetSinTareas();
        if(cursor1.moveToFirst()){
            idUser = cursor1.getLong(0);
            permisoAsignados = buscarPermisoTarea(idUser,tarea);
        }
        cursor1.close();

        if(idUser != null && permisoAsignados != null) {
            addTareaUser(idUser,tarea);
        }else{
            String idsComprobados = "";
            int i = 0;
            do{
                if(i == 0){
                    listIdUser = Util.buscarUserMenosTareas(null);
                    idsComprobados += String.valueOf(listIdUser.get(i));
                }else{
                    listIdUser = Util.buscarUserMenosTareas(idsComprobados);
                    idsComprobados += ","+String.valueOf(listIdUser.get(0));
                }
                permisoAsignados = buscarPermisoTarea(listIdUser.get(0),tarea);
                idUser = listIdUser.get(0);
                i++;
            }while(permisoAsignados == null);

            if(permisoAsignados != null){
                addTareaUser(idUser,tarea);
            }
        }
    }

    private PermisoTareasUser buscarPermisoTarea(Long item, Tarea tarea) {
        List<PermisoTareasUser> l = BaseApplication.getInstance().getPermisoTareasUserDao().queryBuilder().list();
        QueryBuilder<PermisoTareasUser> qb = BaseApplication.getInstance().getPermisoTareasUserDao().queryBuilder();
        qb.where(PermisoTareasUserDao.Properties.IdUser.eq(item), PermisoTareasUserDao.Properties.IdTarea.eq(tarea.getId()));
        PermisoTareasUser permisoAsignados = qb.unique();

        return permisoAsignados;
    }

    private void addTareaUser(Long idUser, Tarea tarea) {
        long insertado = 0;
        RelacionUsuarioTarea relAsiganada = BaseApplication.getInstance().getRelacionUserTareaDao().queryBuilder()
                .where(RelacionUsuarioTareaDao.Properties.IdTarea.eq(tarea.getId()),
                        RelacionUsuarioTareaDao.Properties.IdUser.eq(idUser),
                        RelacionUsuarioTareaDao.Properties.Estado.eq(BaseApplication.getInstance().getString(R.string.completa))).unique();

        if(relAsiganada != null){

            Util.actualizaRelUserTarea(idUser,tarea.getId(),BaseApplication.getInstance().getString(R.string.incompleta));
            insertado = 1;
        }else{
            relAsiganada = new RelacionUsuarioTarea();
            relAsiganada.setIdUser(idUser);
            relAsiganada.setIdTarea(tarea.getId());
            relAsiganada.setEstado(BaseApplication.getInstance().getString(R.string.incompleta));
            insertado = BaseApplication.getInstance().getRelacionUserTareaDao().insert(relAsiganada);
        }

        adminView.showMsgResult(insertado);
        if(insertado > 0){
            adminView.resetDescripcion();
            adminView.resetDuracion();
            adminView.setArrayListidUsers();
        }

    }


}
