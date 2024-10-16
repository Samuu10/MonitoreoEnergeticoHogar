package com.example.monitoreoenergeticohogar.ui.Historial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.monitoreoenergeticohogar.R;
import java.util.List;

//Adaptador para el RecyclerView de historial de consumo
public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>{

    //Variables
    private List<ConsumoHistorial> historialList;

    //Constructor
    public HistorialAdapter(List<ConsumoHistorial> historialList) {
        this.historialList = historialList;
    }

    //Método para crear el ViewHolder
    @NonNull
    @Override
    public HistorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial, parent, false);
        return new HistorialViewHolder(view);
    }

    //Método para enlazar los datos con el ViewHolder
    @Override
    public void onBindViewHolder(@NonNull HistorialViewHolder holder, int position) {
        ConsumoHistorial historial = historialList.get(position);
        holder.mesTextView.setText(historial.getMes());
        holder.consumoTextView.setText(String.valueOf(historial.getConsumo()) + " kW/h");
        holder.precioTextView.setText("$" + String.valueOf(historial.getPrecio()));
    }

    //Método para obtener el número de elementos en la lista
    @Override
    public int getItemCount() {
        return historialList.size();
    }

    //Clase estática para el ViewHolder
    static class HistorialViewHolder extends RecyclerView.ViewHolder {
        TextView mesTextView, consumoTextView, precioTextView;

        public HistorialViewHolder(@NonNull View itemView) {
            super(itemView);
            mesTextView = itemView.findViewById(R.id.mes_text_view);
            consumoTextView = itemView.findViewById(R.id.consumo_text_view);
            precioTextView = itemView.findViewById(R.id.precio_text_view);
        }
    }
}
