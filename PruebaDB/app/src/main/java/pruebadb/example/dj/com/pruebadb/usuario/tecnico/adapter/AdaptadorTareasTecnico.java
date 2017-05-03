package pruebadb.example.dj.com.pruebadb.usuario.tecnico.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import pruebadb.example.dj.com.pruebadb.R;
import pruebadb.example.dj.com.pruebadb.common.base.BaseApplication;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.RelacionUsuarioTarea;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.RelacionUsuarioTareaDao;
import pruebadb.example.dj.com.pruebadb.pruebadb.db.Tarea;
import pruebadb.example.dj.com.pruebadb.usuario.tecnico.contract.TecnicoContract;

/**
 * Created by neon2004 on 01/05/2017.
 */

public class AdaptadorTareasTecnico  extends RecyclerView.Adapter<AdaptadorTareasTecnico.TareasTecnicoViewHolder> {
    private ArrayList<Tarea> datos;
    public TecnicoContract.View tecnicoView;
    private boolean[] checkedHolder;
    private Long idUser;


    public AdaptadorTareasTecnico(ArrayList<Tarea> datos, Long id) {
        this.datos = datos;
        checkedHolder = new boolean[datos.size()];
        this.idUser = id;
    }

    @Override
    public TareasTecnicoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup viewGroup;
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_tareas_user, parent, false);

        TareasTecnicoViewHolder tvh = new TareasTecnicoViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(TareasTecnicoViewHolder holder, int position) {
        Tarea item = datos.get(position);

        holder.bindTarea(item, position);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    //...

    public class TareasTecnicoViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtTarea;
        private TextView txtTextCompletado;
        private TextView tvDuracion;
        private CheckBox chkTareaUser;


        public TareasTecnicoViewHolder(View itemView) {
            super(itemView);

            txtTarea = (TextView)itemView.findViewById(R.id.tvTituloTarea);
            txtTextCompletado = (TextView)itemView.findViewById(R.id.tvTextCompleta);
            tvDuracion  = (TextView)itemView.findViewById(R.id.tvDuracion);
            chkTareaUser = (CheckBox)itemView.findViewById(R.id.chkTareaUser);


        }

        public void bindTarea(final Tarea t, int position) {
            txtTarea.setText(t.getDescripcion());
            tvDuracion.setText(String.valueOf(t.getDuracion())+" H");

            RelacionUsuarioTarea rel = BaseApplication.getInstance().getRelacionUserTareaDao().queryBuilder()
                    .where(RelacionUsuarioTareaDao.Properties.IdTarea.eq(t.getId()),
                            RelacionUsuarioTareaDao.Properties.IdUser.eq(idUser)).unique();

            if(rel.getEstado().equals(BaseApplication.getInstance().getString(R.string.completa))){
                txtTextCompletado.setVisibility(View.VISIBLE);
                tvDuracion.setVisibility(View.GONE);
                chkTareaUser.setVisibility(View.GONE);
            }else{
                txtTextCompletado.setVisibility(View.GONE);
                tvDuracion.setVisibility(View.VISIBLE);
                chkTareaUser.setVisibility(View.VISIBLE);
            }

            chkTareaUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        tecnicoView.tareaMarcadaAccion(t.getId(), true);
                    }else{
                        tecnicoView.tareaMarcadaAccion(t.getId(), false);
                    }

                }
            });

        }
    }

}
