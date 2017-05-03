package pruebadb.example.dj.com.pruebadb.usuario.administrador.contract;

import java.util.ArrayList;
import java.util.List;

import pruebadb.example.dj.com.pruebadb.common.Adapter.AdaptadorDataService;
import pruebadb.example.dj.com.pruebadb.common.Adapter.AdaptadorDataServiceBD;
import pruebadb.example.dj.com.pruebadb.common.rest.Fruit;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.DataService;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Usuario;

/**
 * Created by neon2004 on 30/04/2017.
 */

public class AdministradorContract {
    public interface Presenter{
        void crearTarea(String descripcion, int duracion, ArrayList<Long> usUser);
        void createAdapter(ArrayList<Fruit> listFrutas);
        void createAdapterBD(List<DataService> listDataService);
    }

    public interface View {
        void resetDescripcion();
        void resetDuracion();
        void showMsgResult(long insertado);
        void setArrayListidUsers();
        void setAdapterSpinnerList(List<Usuario> usuarios);
        void setVisivilityReciclerView(boolean show);
        void setVisivilityAltaTarea(boolean show);
        void setAdapterReciclerView(AdaptadorDataService adapter);
        void setAdapterReciclerViewBD(AdaptadorDataServiceBD adaptadorBD);
    }
}
