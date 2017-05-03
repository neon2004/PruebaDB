package pruebadb.example.dj.com.pruebadb.pruebadb.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DATA_SERVICE".
*/
public class DataServiceDao extends AbstractDao<DataService, Long> {

    public static final String TABLENAME = "DATA_SERVICE";

    /**
     * Properties of entity DataService.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Descripcion = new Property(1, String.class, "descripcion", false, "DESCRIPCION");
        public final static Property Categoria = new Property(2, String.class, "categoria", false, "CATEGORIA");
        public final static Property Telefono = new Property(3, String.class, "telefono", false, "TELEFONO");
        public final static Property ZipCode = new Property(4, String.class, "zipCode", false, "ZIP_CODE");
    }


    public DataServiceDao(DaoConfig config) {
        super(config);
    }
    
    public DataServiceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DATA_SERVICE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"DESCRIPCION\" TEXT," + // 1: descripcion
                "\"CATEGORIA\" TEXT," + // 2: categoria
                "\"TELEFONO\" TEXT," + // 3: telefono
                "\"ZIP_CODE\" TEXT);"); // 4: zipCode
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DATA_SERVICE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DataService entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String descripcion = entity.getDescripcion();
        if (descripcion != null) {
            stmt.bindString(2, descripcion);
        }
 
        String categoria = entity.getCategoria();
        if (categoria != null) {
            stmt.bindString(3, categoria);
        }
 
        String telefono = entity.getTelefono();
        if (telefono != null) {
            stmt.bindString(4, telefono);
        }
 
        String zipCode = entity.getZipCode();
        if (zipCode != null) {
            stmt.bindString(5, zipCode);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DataService entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String descripcion = entity.getDescripcion();
        if (descripcion != null) {
            stmt.bindString(2, descripcion);
        }
 
        String categoria = entity.getCategoria();
        if (categoria != null) {
            stmt.bindString(3, categoria);
        }
 
        String telefono = entity.getTelefono();
        if (telefono != null) {
            stmt.bindString(4, telefono);
        }
 
        String zipCode = entity.getZipCode();
        if (zipCode != null) {
            stmt.bindString(5, zipCode);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DataService readEntity(Cursor cursor, int offset) {
        DataService entity = new DataService( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // descripcion
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // categoria
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // telefono
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // zipCode
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DataService entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDescripcion(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCategoria(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTelefono(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setZipCode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DataService entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DataService entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DataService entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}