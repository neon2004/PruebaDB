package pruebadb.example.dj.com.pruebadb.common.util;

import android.database.Cursor;
import java.util.ArrayList;

import pruebadb.example.dj.com.pruebadb.common.base.BaseApplication;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.RelacionUsuarioTareaDao;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Tarea;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.TareaDao;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.UsuarioDao;

/**
 * Created by neon2004 on 30/04/2017.
 */

public class Util {
    public static int generateRandom(int valor){
        int aleatorio;
        aleatorio = (int) (Math.random() * valor) + 1;

        return aleatorio;
    }

    public static boolean returnBooleano(int valor){
        boolean permisoValor;
        if(valor == 0){
            permisoValor = false;
        }else{
            permisoValor = true;
        }

        return permisoValor;
    }

    public static ArrayList<Long> buscarUserMenosTareas(String idUserPasado){
        ArrayList<Long> listIdUser = new ArrayList<Long>();
        String query;

        if(idUserPasado != null){
             query = "SELECT COUNT ("+ RelacionUsuarioTareaDao.Properties.IdUser.columnName+"), "
                + RelacionUsuarioTareaDao.Properties.IdUser.columnName+ " from "
                + RelacionUsuarioTareaDao.TABLENAME + " WHERE "
                + RelacionUsuarioTareaDao.Properties.IdUser.columnName + " not in ("
                + idUserPasado + ") GROUP BY "
                + RelacionUsuarioTareaDao.Properties.IdUser.columnName + " ORDER BY "
                + RelacionUsuarioTareaDao.Properties.IdUser.columnName + " DESC";
        }else{
            query = "SELECT COUNT ("+ RelacionUsuarioTareaDao.Properties.IdUser.columnName+"), "
                + RelacionUsuarioTareaDao.Properties.IdUser.columnName+ " from "
                + RelacionUsuarioTareaDao.TABLENAME + " GROUP BY "
                + RelacionUsuarioTareaDao.Properties.IdUser.columnName + " ORDER BY "
                + RelacionUsuarioTareaDao.Properties.IdUser.columnName + " DESC";
        }

        Cursor cursor =  BaseApplication.getInstance().getDaoSession().getDatabase().rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                listIdUser.add(cursor.getLong(1));
            }while(cursor.moveToNext());
        }
        cursor.close();

        return listIdUser;
    }

    public static Cursor getUsetSinTareas(){
        String query1 = "SELECT u."
                + UsuarioDao.Properties.Id.columnName+" FROM "
                + UsuarioDao.TABLENAME + " u  LEFT JOIN "
                + RelacionUsuarioTareaDao.TABLENAME + " rut ON u."
                + UsuarioDao.Properties.Id.columnName + " = rut." + RelacionUsuarioTareaDao.Properties.IdUser.columnName + " WHERE rut."
                + RelacionUsuarioTareaDao.Properties.IdUser.columnName+" IS NULL AND "
                + UsuarioDao.Properties.TipoUsuario.columnName + " <> '" + UsuarioType.Administrador.name()+"'  ORDER BY u. "
                + UsuarioDao.Properties.Id.columnName + " ASC LIMIT 1";

        Cursor cursor = BaseApplication.getInstance().getDaoSession().getDatabase().rawQuery(query1, null);

       return cursor;
    }

    public static ArrayList<Tarea> getTareas(String idsTarea){
        ArrayList<Tarea> listTareas = new ArrayList<Tarea>();
        String query;

        query = "SELECT * from "
            + TareaDao.TABLENAME + " WHERE "
            + TareaDao.Properties.Id.columnName + " in ("
            + idsTarea + ")";

        Cursor cursor =  BaseApplication.getInstance().getDaoSession().getDatabase().rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Tarea tarea = new Tarea(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
                listTareas.add(tarea);
            }while(cursor.moveToNext());
        }
        cursor.close();

        return listTareas;
    }

    public static void actualizaRelUserTarea(Long idUser, Long idTarea, String estado){
        String query1 = "UPDATE "
                + RelacionUsuarioTareaDao.TABLENAME +" SET "
                + RelacionUsuarioTareaDao.Properties.Estado.columnName+" = '"
                + estado + "' WHERE "
                + RelacionUsuarioTareaDao.Properties.IdUser.columnName + " = " +idUser+ " AND "
                + RelacionUsuarioTareaDao.Properties.IdTarea.columnName + " = " + idTarea;

//        BaseApplication.getInstance().getDaoSession().getDatabase().rawQuery(query1, null);

        BaseApplication.getInstance().getDaoSession().getDatabase().execSQL(query1);


    }
}
