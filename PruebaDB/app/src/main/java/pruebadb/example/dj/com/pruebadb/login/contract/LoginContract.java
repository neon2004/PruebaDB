package pruebadb.example.dj.com.pruebadb.login.contract;


import pruebadb.example.dj.com.pruebadb.pruebadb.db.Usuario;

/**
 * Created by neon2004 on 30/04/2017.
 */

public class LoginContract  {
    public interface Presenter{

        void comprobarCredenciales(String user, String pass);


    }

    public interface View {
        void LanzarViewUsuario(Usuario tipoUsuario);
        void errorUser();

    }
}
