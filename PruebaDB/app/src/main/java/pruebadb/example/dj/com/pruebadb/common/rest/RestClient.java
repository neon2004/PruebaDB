package pruebadb.example.dj.com.pruebadb.common.rest;

import android.util.Log;

import java.util.ArrayList;

import pruebadb.example.dj.com.pruebadb.common.base.BaseActivity;
import pruebadb.example.dj.com.pruebadb.usuario.presenter.UsuarioPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neon2004 on 29/01/2017.
 */

public class RestClient {
    private BaseActivity act;
    private UsuarioPresenter userPresenter;
    private IRestClient interfaces;

    public RestClient(IRestClient interfaces, UsuarioPresenter usuarioPresenter) {
        userPresenter = usuarioPresenter;
        this.interfaces = interfaces;
    }

    public void getListDatas(){
        final ArrayList<Fruit> lisresult = new ArrayList<Fruit>();

        Call<ArrayList<Fruit>> call = interfaces.getData ();
        call.enqueue(new Callback<ArrayList<Fruit>>() {
            @Override
            public void onResponse(Call<ArrayList<Fruit>> call, Response<ArrayList<Fruit>> response) {
                if(response.isSuccessful()){

                    Log.e("OK","PETICION OK: "+response.body());
                    userPresenter.setDataService(response.body());

                }else{
                    Log.e("ERROR","ERROR DATOS: "+response.message());
                    response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Fruit>> call, Throwable t) {
                Log.e("ERROR","ERROR PETICION: "+t.getMessage());
            }
        });
    }
}
