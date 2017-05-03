package pruebadb.example.dj.com.pruebadb.usuario.tecnico.contract;

import java.util.List;

import pruebadb.example.dj.com.pruebadb.common.Adapter.AdaptadorDataServiceBD;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.DataService;
import pruebadb.example.dj.com.pruebadb.usuario.tecnico.adapter.AdaptadorTareasTecnico;

/**
 * Created by neon2004 on 30/04/2017.
 */

public class TecnicoContract {
    public interface Presenter{
        void completarTareaUser(Long valor);
        void createAdapterBD(List<DataService> listDataService);


    }

    public interface View {

       void setAdapterReciclerView(AdaptadorTareasTecnico adapter);
        void tareaMarcadaAccion(Long idTarea, boolean b);
        void setVisibilityCabeceraTareas(boolean show);
        void setAdapterReciclerViewBD(AdaptadorDataServiceBD adaptadorBD);



    }
}
