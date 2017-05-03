package pruebadb.example.dj.com.pruebadb.usuario.administrador.view;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.androidbuts.multispinnerfilter.SpinnerListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pruebadb.example.dj.com.pruebadb.R;
import pruebadb.example.dj.com.pruebadb.common.Adapter.AdaptadorDataService;
import pruebadb.example.dj.com.pruebadb.common.Adapter.AdaptadorDataServiceBD;
import pruebadb.example.dj.com.pruebadb.common.base.BaseFragment;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Usuario;
import pruebadb.example.dj.com.pruebadb.usuario.administrador.contract.AdministradorContract;
import pruebadb.example.dj.com.pruebadb.usuario.administrador.presenter.AdministradorPresenter;

/**
 * Created by neon2004 on 30/04/2017.
 */

@EFragment(R.layout.administrador_fragment)
public class AdministradorFragment extends BaseFragment implements AdministradorContract.View {

    private AdministradorPresenter mPresenter;

    @FragmentArg
    Usuario usuarioLogin;

    @ViewById(R.id.recyclerView)
    RecyclerView recView;
    @ViewById(R.id.scroll)
    ScrollView scrool;

    @ViewById(R.id.etDescripcionTarea)
    EditText etDescripcion;
    @ViewById(R.id.etDuracionTarea)
    EditText etDuracion;
    @ViewById(R.id.searchMultiSpinnerUnlimited)
    MultiSpinnerSearch spinner;

    ArrayList<Long> arrayListidUsers = new ArrayList<Long>();

    @AfterViews
    protected void administradorFragmentAfterViews() {
        mPresenter = new AdministradorPresenter(this);
        recView.setHasFixedSize(true);
    }


    @Click(R.id.btnOkAddTarea)
    void clickAddTarea(View v){

        if(!etDescripcion.getText().toString().isEmpty() && !etDuracion.getText().toString().isEmpty()&& !arrayListidUsers.isEmpty() ){
            mPresenter.crearTarea(etDescripcion.getText().toString(),Integer.parseInt(etDuracion.getText().toString()), arrayListidUsers);
        }else{
            showSnackbar(getActivity().getResources().getString(R.string.error_datos_sin_rellenar));
        }
    }

    @Override
    public void resetDescripcion() {
        etDescripcion.setText("");
    }

    @Override
    public void resetDuracion() {
        etDuracion.setText("");
    }

    @Override
    public void showMsgResult(long insertado) {
        if(insertado > 0){
            showSnackbar(getActivity().getString(R.string.tarea_creada));
            resetDuracion();
            resetDescripcion();
        }else{
            showSnackbar(getActivity().getString(R.string.error_crear_tarea));
        }
    }

    @Override
    public void setArrayListidUsers() {
        arrayListidUsers.clear();
    }

    @Override
    public void setAdapterSpinnerList(List<Usuario> usuarios) {

        final List<KeyPairBoolData> listArray0 = new ArrayList<>();
        for (Usuario item:usuarios) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(item.getId());
            h.setName(item.getNombre());
            h.setSelected(false);
            listArray0.add(h);
        }

        spinner.setItems(listArray0, -1, new SpinnerListener() {

            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        arrayListidUsers.add(items.get(i).getId());

                    }
                }
            }
        });
    }

    @Override
    public void setVisivilityReciclerView(boolean show) {
        if(show){
            recView.setVisibility(View.VISIBLE);
        }else{
            recView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setVisivilityAltaTarea(boolean show) {
        if(show){
            scrool.setVisibility(View.VISIBLE);
        }else{
            scrool.setVisibility(View.GONE);
        }
    }

    public AdministradorContract.Presenter getmPresenter() {
        return mPresenter;
    }

    @Override
    public void setAdapterReciclerView(AdaptadorDataService adapter) {
        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }


    @Override
    public void setAdapterReciclerViewBD(AdaptadorDataServiceBD adapter) {
        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

    }


}
