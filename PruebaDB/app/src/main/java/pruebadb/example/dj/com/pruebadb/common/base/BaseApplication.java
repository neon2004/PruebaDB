package pruebadb.example.dj.com.pruebadb.common.base;

import android.app.Application;



import org.androidannotations.annotations.EApplication;
import org.greenrobot.greendao.database.Database;

import java.util.List;

import pruebadb.example.dj.com.pruebadb.R;
import pruebadb.example.dj.com.pruebadb.common.util.TareaType;
import pruebadb.example.dj.com.pruebadb.common.util.UsuarioType;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.DaoMaster;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.DaoSession;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.DataService;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.DataServiceDao;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.PermisoTareasUser;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.PermisoTareasUserDao;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.RelacionUsuarioTarea;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.RelacionUsuarioTareaDao;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Tarea;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.TareaDao;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Usuario;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.UsuarioDao;


/**
 * Created by neon2004 on 29/04/2017.
 */

@EApplication
public class BaseApplication extends Application {

    public static final boolean ENCRYPTED = false;

    private static BaseApplication mInstance;
    private DaoSession daoSession;
    private UsuarioDao userDao;
    private RelacionUsuarioTareaDao relacionUserTareaDao;
    private TareaDao tareaDao;
    private PermisoTareasUserDao permisoTareasUserDao;
    private DataServiceDao dataServiceDao;


    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "pruebaDB-db-encrypted" : "pruebaDB-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        userDao = daoSession.getUsuarioDao();
        relacionUserTareaDao = daoSession.getRelacionUsuarioTareaDao();
        tareaDao = daoSession.getTareaDao();
        permisoTareasUserDao = daoSession.getPermisoTareasUserDao();
        dataServiceDao = daoSession.getDataServiceDao();

        // SI ES EL INICIO Y NO TENEMOS DATOS LOS CREAMOS
        if(userDao.count() == 0) {
            List<DataService> l = dataServiceDao.queryBuilder().list();
            int i = 1;
            for (TareaType item : TareaType.values()) {
                Tarea tarea = new Tarea();
                tarea.setTarea_id(String.valueOf(i));
                tarea.setDescripcion(item.name());
                tarea.setDuracion(i+1);
                tareaDao.insert(tarea);
                i++;
            }


            Usuario user = new Usuario();
            user.setNombre("Usuario1");
            user.setUser_id("1");
            user.setPass("1234");
            user.setCiudad("ciudadUser1");
            user.setNif("1234567891");
            user.setTipoUsuario(UsuarioType.Administrador.name());
            userDao.insertInTx(user);

            for(i = 2; i<= 5; i++) {
                user = new Usuario();
                user.setNombre("Usuario" + i);
                user.setUser_id(String.valueOf(i));
                user.setPass("1234");
                user.setCiudad("ciudadUser" + i);
                user.setNif("123456789" + i);
                user.setTipoUsuario(UsuarioType.Técnico.name());
                userDao.insertInTx(user);

                int j;
                if(i == 2 || i == 3){
                    j = i -1;
                    PermisoTareasUser permiso = new PermisoTareasUser();
                    permiso.setPermiso(true);
                    permiso.setIdTarea(Long.valueOf(j));
                    permiso.setIdUser(user.getId());
                    permisoTareasUserDao.insertInTx(permiso);

                    RelacionUsuarioTarea rel = new RelacionUsuarioTarea();
                    rel.setIdTarea(Long.valueOf(j));
                    rel.setIdUser(user.getId());
                    rel.setEstado(getString(R.string.incompleta));
                    getRelacionUserTareaDao().insertInTx(rel);
                }

//                int numAleatorio = Util.generateRandom(10);
//                if(numAleatorio % 2 == 0 ){
//                    int idTareaAleatorio = Util.generateRandom(TareaType.values().length);
//                    PermisoTareasUser permiso = new PermisoTareasUser();
//                    permiso.setPermiso(true);
//                    permiso.setIdTarea(Long.valueOf(idTareaAleatorio));
//                    permiso.setIdUser(user.getId());
//                    permisoTareasUserDao.insertInTx(permiso);
//
//                    RelacionUsuarioTarea rel = new RelacionUsuarioTarea();
//                    rel.setIdTarea(Long.valueOf(idTareaAleatorio));
//                    rel.setIdUser(user.getId());
//                    rel.setEstado(getString(R.string.incompleta));
//                    getRelacionUserTareaDao().insertInTx(rel);
//                }
            }
        }

//
//        List<RelacionUsuarioTarea> l = BaseApplication.getInstance().getRelacionUserTareaDao().queryBuilder().list();
//
////        String[] colums = BaseApplication.getInstance().getRelacionUserTareaDao().getAllColumns();
////        String sql = "COUNT(T."+ colums[2] +") AS countUser, T."+ colums[2] +" FROM T GROUP BY T"+ colums[2]+ " ORDER BY countUser LIMIT 1";
//
//        String query = "SELECT COUNT ("
//                + RelacionUsuarioTareaDao.Properties.IdUser.columnName+
//                "), "
//                + RelacionUsuarioTareaDao.Properties.IdUser.columnName+ " from "
//                + RelacionUsuarioTareaDao.TABLENAME + " GROUP BY "
//                + RelacionUsuarioTareaDao.Properties.IdUser.columnName + " ORDER BY "
//                + RelacionUsuarioTareaDao.Properties.IdUser.columnName + " ASC LIMIT 1";
//
//        Integer count = 0;
//        Long idUser = null;
//
//
//        Cursor cursor =
//                BaseApplication.getInstance().getDaoSession().getDatabase().rawQuery(
//                        query, null
//                );
//
//        if(cursor.moveToFirst()){
//            count = cursor.getInt(0);
//            idUser = cursor.getLong(1);
//        }
//        cursor.close();




    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public UsuarioDao getUserDao() {
        return userDao;
    }

    public RelacionUsuarioTareaDao getRelacionUserTareaDao() {
        return relacionUserTareaDao;
    }

    public TareaDao getTareaDao() {
        return tareaDao;
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

    public PermisoTareasUserDao getPermisoTareasUserDao() {
        return permisoTareasUserDao;
    }

    public DataServiceDao getDataServiceDao() {
        return dataServiceDao;
    }
}
