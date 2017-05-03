package pruebadb.example.dj.com.pruebadb.common.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;

import pruebadb.example.dj.com.pruebadb.R;
import pruebadb.example.dj.com.pruebadb.common.rest.Fruit;

/**
 * Created by neon2004 on 01/05/2017.
 */

public class AdaptadorDataService extends RecyclerView.Adapter<AdaptadorDataService.DataServiceViewHolder> {
    private ArrayList<Fruit> datos;

    public AdaptadorDataService(ArrayList<Fruit> datos) {
        this.datos = datos;

    }

    @Override
    public DataServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup viewGroup;
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_data_service, parent, false);

        DataServiceViewHolder tvh = new DataServiceViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(DataServiceViewHolder holder, int position) {
        Fruit item = datos.get(position);

        holder.bindTarea(item, position);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    //...

    public class DataServiceViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtDescripcion;
        private TextView txtCategoria;
        private TextView tvTelefono;
        private TextView tvZipCode;


        public DataServiceViewHolder(View itemView) {
            super(itemView);

            txtDescripcion = (TextView)itemView.findViewById(R.id.tvItemServiceData);
            txtCategoria = (TextView)itemView.findViewById(R.id.tvCategoryServiceData);
            tvTelefono  = (TextView)itemView.findViewById(R.id.tvTelefonoServiceData);
            tvZipCode = (TextView)itemView.findViewById(R.id.tvZipCodeServiceData);


        }

        public void bindTarea(Fruit t, int position) {
            txtDescripcion.setText(t.getItem());
            txtCategoria.setText(t.getCategory());
            tvTelefono.setText(t.getPhone1());
            tvZipCode.setText(t.getZipcode());

        }
    }

}
