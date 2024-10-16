package com.example.monitoreoenergeticohogar.ui.Recomendaciones;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.monitoreoenergeticohogar.R;
import java.util.List;

//Adaptador para el RecyclerView de las recomendaciones
public class RecomendacionAdapter extends RecyclerView.Adapter<RecomendacionAdapter.RecomendacionViewHolder> {

    //Variables
    private List<Recomendacion> recomendaciones;

    //Constructor
    public RecomendacionAdapter(List<Recomendacion> recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    //Método para crear el ViewHolder
    @NonNull
    @Override
    public RecomendacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recomendacion, parent, false);
        return new RecomendacionViewHolder(view);
    }

    //Método para enlazar los datos con el ViewHolder
    @Override
    public void onBindViewHolder(@NonNull RecomendacionViewHolder holder, int position) {
        Recomendacion recomendacion = recomendaciones.get(position);
        holder.tituloTextView.setText(recomendacion.getTitulo());
        holder.descripcionTextView.setText(recomendacion.getDescripcion());
    }

    //Método para obtener el número de elementos en la lista
    @Override
    public int getItemCount() {
        return recomendaciones.size();
    }

    //Clase estática para el ViewHolder
    static class RecomendacionViewHolder extends RecyclerView.ViewHolder {
        TextView tituloTextView, descripcionTextView;

        public RecomendacionViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.titulo_text_view);
            descripcionTextView = itemView.findViewById(R.id.descripcion_text_view);
        }
    }
}
