package pruebadb.example.dj.com.pruebadb.usuario.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;



import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pruebadb.example.dj.com.pruebadb.R;
import pruebadb.example.dj.com.pruebadb.common.base.BaseActivity;
import pruebadb.example.dj.com.pruebadb.common.rest.Fruit;
import pruebadb.example.dj.com.pruebadb.common.util.UsuarioType;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.DataService;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Usuario;
import pruebadb.example.dj.com.pruebadb.usuario.administrador.presenter.AdministradorPresenter;
import pruebadb.example.dj.com.pruebadb.usuario.administrador.view.AdministradorFragment;
import pruebadb.example.dj.com.pruebadb.usuario.administrador.view.AdministradorFragment_;
import pruebadb.example.dj.com.pruebadb.usuario.contract.UsuarioContract;
import pruebadb.example.dj.com.pruebadb.usuario.presenter.UsuarioPresenter;
import pruebadb.example.dj.com.pruebadb.usuario.tecnico.presenter.TecnicoPresenter;
import pruebadb.example.dj.com.pruebadb.usuario.tecnico.view.TecnicoFragment;
import pruebadb.example.dj.com.pruebadb.usuario.tecnico.view.TecnicoFragment_;

/**
 * Created by neon2004 on 30/04/2017.
 */

@EActivity(R.layout.activity_usuario)
public class UsuarioActivity extends BaseActivity implements UsuarioContract.View{
    @Extra
    Usuario usuarioLogin;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.txtAbTitulo)
    TextView tvTituloToolbar;
    @ViewById(R.id.txtAbSubTitulo)
    TextView tvSubTituloToolbar;

    AdministradorFragment adminFragment;
    TecnicoFragment tecnicoFragment;
    Fragment actualFragment;

    TecnicoPresenter tecnicoPresenter;
    AdministradorPresenter adminPresenter;
    private UsuarioPresenter mPresenter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        if(actualFragment instanceof AdministradorFragment){
            getMenuInflater().inflate(R.menu.menu_admin, menu);

        }else{
            getMenuInflater().inflate(R.menu.menu_tecnico, menu);
        }

        return true;
    }

    @AfterViews
    protected void usuarioActivityAfterViews() {
        crearRetrofitGson();
        mPresenter = new UsuarioPresenter(getInterfaces(),this);



        //Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(usuarioLogin.getTipoUsuario().equals(UsuarioType.Administrador.name())) {
            tvTituloToolbar.setText(UsuarioType.Administrador.name());
            tvSubTituloToolbar.setText(R.string.crear_tarea);

            adminFragment = AdministradorFragment_.builder().usuarioLogin(usuarioLogin).build();

            transaction.replace(R.id.sample_content_fragment, adminFragment);
            actualFragment = adminFragment;

        }else {
            tvTituloToolbar.setText(UsuarioType.Técnico.name());
            tvSubTituloToolbar.setText(R.string.tareas_asignadas);

            tecnicoFragment = TecnicoFragment_.builder().usuarioLogin(usuarioLogin).build();
            transaction.replace(R.id.sample_content_fragment, tecnicoFragment);
            actualFragment = tecnicoFragment;
        }
        transaction.commit();


    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(tecnicoFragment != null){
            tecnicoPresenter = (TecnicoPresenter) tecnicoFragment.getmPresenter();
        }
        switch (item.getItemId()){
            case R.id.MnuCerrarSesion:
                finish();
                break;
            case R.id.MnuCompletarTarea:

                for (Long valor:tecnicoFragment.getListTareasSelected()) {
                    tecnicoPresenter.completarTareaUser(valor);

                }
                break;
            case R.id.MnuGetDataService:
               mPresenter.getDataService();
                break;
            case R.id.MnuTarea:
                adminFragment.setVisivilityAltaTarea(true);
                adminFragment.setVisivilityReciclerView(false);
                break;
            case R.id.MnuTareaAsignadas:
                tecnicoFragment.setVisibilityCabeceraTareas(true);
                tecnicoPresenter.init();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    public void setDataService(ArrayList<Fruit> listdata) {
        if(actualFragment instanceof AdministradorFragment){
            adminPresenter = (AdministradorPresenter) adminFragment.getmPresenter();
            adminFragment.setVisivilityAltaTarea(false);
            adminFragment.setVisivilityReciclerView(true);
            adminPresenter.createAdapter(listdata);

        }else{
            tecnicoFragment.setVisibilityCabeceraTareas(false);
        }

    }

    @Override
    public void setDataServiceBD(List<DataService> listDataService) {
        if(actualFragment instanceof AdministradorFragment){
            adminPresenter = (AdministradorPresenter) adminFragment.getmPresenter();
            adminFragment.setVisivilityAltaTarea(false);
            adminFragment.setVisivilityReciclerView(true);
            adminPresenter.createAdapterBD(listDataService);

        }else{
            tecnicoFragment.setVisibilityCabeceraTareas(false);
            tecnicoPresenter.createAdapterBD(listDataService);
        }
    }
}
