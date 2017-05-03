package pruebadb.example.dj.com.pruebadb.login.view;

import android.view.View;
import android.widget.EditText;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pruebadb.example.dj.com.pruebadb.R;
import pruebadb.example.dj.com.pruebadb.common.base.BaseActivity;
import pruebadb.example.dj.com.pruebadb.login.contract.LoginContract;
import pruebadb.example.dj.com.pruebadb.login.presenter.LoginPresenter;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Usuario;
import pruebadb.example.dj.com.pruebadb.usuario.view.UsuarioActivity_;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginContract.View{



    @ViewById(R.id.etUser)
    EditText etUser;
    @ViewById(R.id.etPass)
    EditText etPass;

    LoginPresenter mPresenter;

    @AfterViews
    protected void loginActivityAfterViews() {
        mPresenter = new LoginPresenter(this);



    }

    @Click(R.id.btnAceptarLogin)
    public void OnClickLogin(View v) {

        comprobarCredenciales(etUser.getText().toString(),etPass.getText().toString());
    }

    @Background
    void comprobarCredenciales(String user, String pass) {
        mPresenter.comprobarCredenciales(user,pass);

    }


    @Override
    public void LanzarViewUsuario(Usuario usuario) {
        UsuarioActivity_.intent(this)
        .usuarioLogin(usuario)
        .start();

    }

    @Override
    public void errorUser() {
        showSnackbar(getString(R.string.errorUser));
    }
}
