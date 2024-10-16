package com.example.monitoreoenergeticohogar.ui.Activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoreoenergeticohogar.R;
import com.example.monitoreoenergeticohogar.ui.Firebase.FirebaseHelper;
import com.example.monitoreoenergeticohogar.ui.Historial.ConsumoHistorial;
import com.example.monitoreoenergeticohogar.ui.Historial.HistorialAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Clase para mostrar el historial de consumo de energía del hogar
public class HistorialActivity extends AppCompatActivity implements FirebaseHelper.OnDataLoadedListener {

    //Variables
    private Button btnVolver;
    private RecyclerView recyclerView;
    private HistorialAdapter adapter;
    private List<ConsumoHistorial> historialList;
    private FirebaseHelper firebaseHelper;
    private MutableLiveData<String> error = new MutableLiveData<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historialList = new ArrayList<>();
        adapter = new HistorialAdapter(historialList);
        recyclerView.setAdapter(adapter);

        firebaseHelper = new FirebaseHelper(this);
        fetchHistorialData();

        //Botón para volver al MainActivity
        btnVolver = findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(v -> finish());
    }

    private void fetchHistorialData() {
        firebaseHelper.cargarHistorialConsumo();
    }

    @Override
    public void onHistorialLoaded(List<ConsumoHistorial> historialList) {
        this.historialList.clear();
        this.historialList.addAll(historialList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDataLoaded(double consumoActual, double consumoMensual) {
        //No hace falta implementar nada para esta actividad
    }

    @Override
    public void onDataLoadedElectrodomesticos(HashMap<String, Double> consumoElectrodomesticos) {
        //No hace falta implementar nada para esta actividad
    }

    @Override
    public void onDataLoadedHabitaciones(HashMap<String, Double> consumoHabitaciones) {
        //No hace falta implementar nada para esta actividad
    }

    @Override
    public void onError(String error) {
        this.error.postValue(error);
    }
}
