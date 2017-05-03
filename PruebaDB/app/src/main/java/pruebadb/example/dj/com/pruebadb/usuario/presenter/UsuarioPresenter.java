package pruebadb.example.dj.com.pruebadb.usuario.presenter;

import java.util.ArrayList;
import java.util.List;

import pruebadb.example.dj.com.pruebadb.common.base.BaseApplication;
import pruebadb.example.dj.com.pruebadb.common.rest.Fruit;
import pruebadb.example.dj.com.pruebadb.common.rest.IRestClient;
import pruebadb.example.dj.com.pruebadb.common.rest.RestClient;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.DataService;
import pruebadb.example.dj.com.pruebadb.usuario.contract.UsuarioContract;
import pruebadb.example.dj.com.pruebadb.usuario.view.UsuarioActivity;


/**
 * Created by neon2004 on 30/04/2017.
 */

public class UsuarioPresenter implements UsuarioContract.Presenter{
    private UsuarioContract.View usuarioView;
    private RestClient restClient;
    private ArrayList<Fruit> listdata;
    private List<DataService> listDataService;


    public UsuarioPresenter(IRestClient interfaces, UsuarioActivity usuarioActivity) {
        usuarioView =  usuarioActivity;
        restClient = new RestClient(interfaces,this);
    }


    @Override
    public void getDataService() {
        listDataService = BaseApplication.getInstance().getDataServiceDao().queryBuilder().list();
        if(listDataService.isEmpty()){
            restClient.getListDatas();
        }else{
            setDataService(null);
        }
    }

    @Override
    public void setDataService(ArrayList<Fruit> datas) {
        listdata = datas;
        if(listDataService.isEmpty()){
            for (Fruit itemData:listdata) {
                DataService dataService = new DataService();
                dataService.setDescripcion(itemData.getItem());
                dataService.setCategoria(itemData.getCategory());
                dataService.setTelefono(itemData.getPhone1());
                dataService.setZipCode(itemData.getZipcode());
                BaseApplication.getInstance().getDataServiceDao().insertOrReplace(dataService);
            }

            usuarioView.setDataService(listdata);
        }else{
            usuarioView.setDataServiceBD(listDataService);
        }
    }
}
