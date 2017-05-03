package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class Maingenerator {
    private static Entity tarea;
    private static Entity user;

    public static void main(String[] args) {
        Schema schema = new Schema(1, "pruebadb.example.dj.com.pruebadb.pruebadb.db"); // Your app package name and the (.db) is the folder where the DAO files will be generated into.
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addUserEntities(schema);
        addTareasEntities(schema);
        addRelacionUserTareaEntities(schema);
        addPermisoTareasUserEntities(schema);
        addDataServiceEntities(schema);
    }

    private static Entity addTareasEntities(Schema schema) {
        tarea = schema.addEntity("Tarea");
        tarea.addIdProperty().primaryKey().autoincrement();
        tarea.addStringProperty("tarea_id").notNull();
        tarea.addStringProperty("descripcion").notNull();
        tarea.addIntProperty("duracion").notNull();

        return tarea;
    }

    private static Entity addRelacionUserTareaEntities(Schema schema) {
        Entity relUserTaea = schema.addEntity("RelacionUsuarioTarea");
        relUserTaea.addStringProperty("estado").notNull();

        Property idUserRel =  relUserTaea.addLongProperty("idUser").notNull().getProperty();
        Property idTareaRel =  relUserTaea.addLongProperty("idTarea").notNull().getProperty();
        user.addToMany(relUserTaea, idUserRel).setName("userRef");
        tarea.addToMany(relUserTaea, idTareaRel).setName("tareaRef");

        return relUserTaea;
    }

    private static Entity addUserEntities(final Schema schema) {
        user = schema.addEntity("Usuario");
        user.addIdProperty().primaryKey().autoincrement();
        user.addStringProperty("user_id").notNull();
        user.addStringProperty("nombre").notNull();
        user.addStringProperty("nif").notNull();
        user.addStringProperty("pass").notNull();
        user.addStringProperty("tipoUsuario").notNull();
        user.addStringProperty("email");
        user.addStringProperty("direccion");
        user.addStringProperty("cp");
        user.addStringProperty("ciudad");
        return user;
    }

    private static Entity addDataServiceEntities(final Schema schema) {
        Entity dataService = schema.addEntity("DataService");
        dataService.addIdProperty().primaryKey().autoincrement();
        dataService.addStringProperty("descripcion");
        dataService.addStringProperty("categoria");
        dataService.addStringProperty("telefono");
        dataService.addStringProperty("zipCode");

        return user;
    }

    private static Entity addPermisoTareasUserEntities(final Schema schema) {
        Entity permiso = schema.addEntity("PermisoTareasUser");

        permiso.addBooleanProperty("permiso").notNull();

        Property idUserRel =  permiso.addLongProperty("idUser").notNull().getProperty();
        Property idTareaRel =  permiso.addLongProperty("idTarea").notNull().getProperty();
        user.addToMany(permiso, idUserRel).setName("userPermiso");
        tarea.addToMany(permiso, idTareaRel).setName("tareaPermiso");
        return permiso;
    }
}
