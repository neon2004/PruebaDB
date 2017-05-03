package pruebadb.example.dj.com.pruebadb.usuario.contract;

import java.util.ArrayList;
import java.util.List;

import pruebadb.example.dj.com.pruebadb.common.rest.Fruit;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.DataService;

/**
 * Created by neon2004 on 30/04/2017.
 */

public class UsuarioContract {
    public interface Presenter{
        void getDataService();
        void setDataService(ArrayList<Fruit> datas);
    }

    public interface View {
       void setDataService(ArrayList<Fruit> listdata);
        void setDataServiceBD(List<DataService> listDataService);
    }
}
