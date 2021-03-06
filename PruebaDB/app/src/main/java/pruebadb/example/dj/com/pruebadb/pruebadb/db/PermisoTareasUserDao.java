package pruebadb.example.dj.com.pruebadb.pruebadb.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PERMISO_TAREAS_USER".
*/
public class PermisoTareasUserDao extends AbstractDao<PermisoTareasUser, Void> {

    public static final String TABLENAME = "PERMISO_TAREAS_USER";

    /**
     * Properties of entity PermisoTareasUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Permiso = new Property(0, boolean.class, "permiso", false, "PERMISO");
        public final static Property IdUser = new Property(1, long.class, "idUser", false, "ID_USER");
        public final static Property IdTarea = new Property(2, long.class, "idTarea", false, "ID_TAREA");
    }

    private Query<PermisoTareasUser> usuario_UserPermisoQuery;
    private Query<PermisoTareasUser> tarea_TareaPermisoQuery;

    public PermisoTareasUserDao(DaoConfig config) {
        super(config);
    }
    
    public PermisoTareasUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PERMISO_TAREAS_USER\" (" + //
                "\"PERMISO\" INTEGER NOT NULL ," + // 0: permiso
                "\"ID_USER\" INTEGER NOT NULL ," + // 1: idUser
                "\"ID_TAREA\" INTEGER NOT NULL );"); // 2: idTarea
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PERMISO_TAREAS_USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PermisoTareasUser entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getPermiso() ? 1L: 0L);
        stmt.bindLong(2, entity.getIdUser());
        stmt.bindLong(3, entity.getIdTarea());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PermisoTareasUser entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getPermiso() ? 1L: 0L);
        stmt.bindLong(2, entity.getIdUser());
        stmt.bindLong(3, entity.getIdTarea());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public PermisoTareasUser readEntity(Cursor cursor, int offset) {
        PermisoTareasUser entity = new PermisoTareasUser( //
            cursor.getShort(offset + 0) != 0, // permiso
            cursor.getLong(offset + 1), // idUser
            cursor.getLong(offset + 2) // idTarea
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PermisoTareasUser entity, int offset) {
        entity.setPermiso(cursor.getShort(offset + 0) != 0);
        entity.setIdUser(cursor.getLong(offset + 1));
        entity.setIdTarea(cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(PermisoTareasUser entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(PermisoTareasUser entity) {
        return null;
    }

    @Override
    public boolean hasKey(PermisoTareasUser entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "userPermiso" to-many relationship of Usuario. */
    public List<PermisoTareasUser> _queryUsuario_UserPermiso(long idUser) {
        synchronized (this) {
            if (usuario_UserPermisoQuery == null) {
                QueryBuilder<PermisoTareasUser> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.IdUser.eq(null));
                usuario_UserPermisoQuery = queryBuilder.build();
            }
        }
        Query<PermisoTareasUser> query = usuario_UserPermisoQuery.forCurrentThread();
        query.setParameter(0, idUser);
        return query.list();
    }

    /** Internal query to resolve the "tareaPermiso" to-many relationship of Tarea. */
    public List<PermisoTareasUser> _queryTarea_TareaPermiso(long idTarea) {
        synchronized (this) {
            if (tarea_TareaPermisoQuery == null) {
                QueryBuilder<PermisoTareasUser> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.IdTarea.eq(null));
                tarea_TareaPermisoQuery = queryBuilder.build();
            }
        }
        Query<PermisoTareasUser> query = tarea_TareaPermisoQuery.forCurrentThread();
        query.setParameter(0, idTarea);
        return query.list();
    }

}
