package pruebadb.example.dj.com.pruebadb.pruebadb.db;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "RELACION_USUARIO_TAREA".
 */
@Entity
public class RelacionUsuarioTarea {

    @NotNull
    private String estado;
    private long idUser;
    private long idTarea;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public RelacionUsuarioTarea() {
    }

    @Generated
    public RelacionUsuarioTarea(String estado, long idUser, long idTarea) {
        this.estado = estado;
        this.idUser = idUser;
        this.idTarea = idTarea;
    }

    @NotNull
    public String getEstado() {
        return estado;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setEstado(@NotNull String estado) {
        this.estado = estado;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(long idTarea) {
        this.idTarea = idTarea;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}