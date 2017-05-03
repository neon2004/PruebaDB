package pruebadb.example.dj.com.pruebadb.login.presenter;



import java.util.List;

import pruebadb.example.dj.com.pruebadb.common.base.BaseApplication;
import pruebadb.example.dj.com.pruebadb.login.contract.LoginContract;
import pruebadb.example.dj.com.pruebadb.login.view.LoginActivity;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Usuario;


/**
 * Created by neon2004 on 30/04/2017.
 */

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View loginView;
    private List<Usuario> usuarios;

    public LoginPresenter(LoginActivity loginActivity) {
        loginView = loginActivity;
        usuarios = BaseApplication.getInstance().getUserDao().queryBuilder().list();
    }


    @Override
    public void comprobarCredenciales(String user, String pass) {
        boolean encontrado = false;
        int i = 0;
        while(i<usuarios.size() && ! encontrado){
            if(usuarios.get(i).getNif().equals(user) && usuarios.get(i).getPass().equals(pass)){
                encontrado = true;
            }else{
                i++;
            }
        }
        if(encontrado){
            accionLogin(usuarios.get(i));
        }else{
            loginView.errorUser();
        }
    }

    private void accionLogin(Usuario tipoUsuario) {
        loginView.LanzarViewUsuario(tipoUsuario);
    }
}
