package pruebadb.example.dj.com.pruebadb.usuario.tecnico.presenter;

import java.util.ArrayList;
import java.util.List;

import pruebadb.example.dj.com.pruebadb.R;
import pruebadb.example.dj.com.pruebadb.common.Adapter.AdaptadorDataServiceBD;
import pruebadb.example.dj.com.pruebadb.common.base.BaseApplication;
import pruebadb.example.dj.com.pruebadb.common.util.Util;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.DataService;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.RelacionUsuarioTarea;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.RelacionUsuarioTareaDao;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Tarea;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Usuario;
import pruebadb.example.dj.com.pruebadb.usuario.tecnico.adapter.AdaptadorTareasTecnico;
import pruebadb.example.dj.com.pruebadb.usuario.tecnico.contract.TecnicoContract;
import pruebadb.example.dj.com.pruebadb.usuario.tecnico.view.TecnicoFragment;


/**
 * Created by neon2004 on 30/04/2017.
 */

public class TecnicoPresenter implements TecnicoContract.Presenter{
    private TecnicoContract.View tecnicoView;

    AdaptadorTareasTecnico adaptador;
    private Usuario user;
    private AdaptadorDataServiceBD adaptadorBD;

    public TecnicoPresenter(TecnicoFragment tecnicoFragment, Usuario usuarioLogint) {
        tecnicoView = tecnicoFragment;
        user = usuarioLogint;
        init();



    }

    public void init() {
        List<RelacionUsuarioTarea>       l = BaseApplication.getInstance().getRelacionUserTareaDao().queryBuilder().list();
        List<RelacionUsuarioTarea> listrelTareasUser = BaseApplication.getInstance().getRelacionUserTareaDao()._queryUsuario_UserRef(user.getId());

        String listidsTarea = "";
        for (RelacionUsuarioTarea tareasUser:listrelTareasUser) {
            listidsTarea += String.valueOf(tareasUser.getIdTarea())+",";
        }

        if(!listidsTarea.equals("")){
            listidsTarea = listidsTarea.substring(0,listidsTarea.lastIndexOf(","));
        }


        ArrayList<Tarea> tareas =  Util.getTareas(listidsTarea);
        adaptador = new AdaptadorTareasTecnico(tareas,user.getId());
        tecnicoView.setAdapterReciclerView(adaptador);
    }

    @Override
    public void completarTareaUser(Long valor) {
        RelacionUsuarioTarea rel =  BaseApplication.getInstance().getRelacionUserTareaDao().queryBuilder()
                .where(RelacionUsuarioTareaDao.Properties.IdTarea.eq(valor),
                        RelacionUsuarioTareaDao.Properties.IdUser.eq(user.getId())).unique();
        rel.setEstado(BaseApplication.getInstance().getString(R.string.completa));

        Util.actualizaRelUserTarea(user.getId(),valor,BaseApplication.getInstance().getString(R.string.completa));

        adaptador.notifyDataSetChanged();

    }

    @Override
    public void createAdapterBD(List<DataService> listDataService) {
        adaptadorBD = new AdaptadorDataServiceBD(listDataService);
        tecnicoView.setAdapterReciclerViewBD(adaptadorBD);
    }
}
