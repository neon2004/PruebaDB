package pruebadb.example.dj.com.pruebadb.usuario.tecnico.view;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import pruebadb.example.dj.com.pruebadb.R;
import pruebadb.example.dj.com.pruebadb.common.Adapter.AdaptadorDataServiceBD;
import pruebadb.example.dj.com.pruebadb.common.base.BaseFragment;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Usuario;
import pruebadb.example.dj.com.pruebadb.usuario.tecnico.adapter.AdaptadorTareasTecnico;
import pruebadb.example.dj.com.pruebadb.usuario.tecnico.contract.TecnicoContract;
import pruebadb.example.dj.com.pruebadb.usuario.tecnico.presenter.TecnicoPresenter;

/**
 * Created by neon2004 on 30/04/2017.
 */

@EFragment(R.layout.tecnico_fragment)
public class TecnicoFragment extends BaseFragment implements TecnicoContract.View{
    TecnicoContract.Presenter mPresenter;

    @ViewById(R.id.recyclerView)
    RecyclerView recView;
    @ViewById(R.id.layoutCabeceraTarea)
    LinearLayout layoutCabeceraTarea;


    @FragmentArg
    Usuario usuarioLogin;

    private ArrayList<Long> listTareasSelected = new ArrayList<Long>();

    public TecnicoContract.Presenter getmPresenter() {
        return mPresenter;
    }

    @AfterViews
    protected void tecnicoFragmentAfterViews() {
        mPresenter = new TecnicoPresenter(this,usuarioLogin);
        recView.setHasFixedSize(true);
    }

    @Override
    public void setAdapterReciclerView(AdaptadorTareasTecnico adapter) {
        recView.setAdapter(adapter);
        adapter.tecnicoView = this;
        recView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }

    @Override
    public void tareaMarcadaAccion(Long idTarea, boolean b) {
        if(b){
            listTareasSelected.add(idTarea);
        }else{
            listTareasSelected.remove(listTareasSelected.indexOf(idTarea));
        }
    }

    @Override
    public void setVisibilityCabeceraTareas(boolean show) {
        if(show){
            layoutCabeceraTarea.setVisibility(View.VISIBLE);
        }else{
            layoutCabeceraTarea.setVisibility(View.GONE);
        }
    }

    @Override
    public void setAdapterReciclerViewBD(AdaptadorDataServiceBD adaptadorBD) {
        recView.setAdapter(adaptadorBD);
        recView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }

    public ArrayList<Long> getListTareasSelected() {
        return listTareasSelected;
    }
}
