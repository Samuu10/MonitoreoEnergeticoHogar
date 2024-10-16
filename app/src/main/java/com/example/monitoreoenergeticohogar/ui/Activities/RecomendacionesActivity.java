package com.example.monitoreoenergeticohogar.ui.Activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoreoenergeticohogar.R;
import com.example.monitoreoenergeticohogar.ui.Firebase.FirebaseHelper;
import com.example.monitoreoenergeticohogar.ui.Historial.Historial;
import com.example.monitoreoenergeticohogar.ui.Recomendaciones.Recomendacion;
import com.example.monitoreoenergeticohogar.ui.Recomendaciones.RecomendacionAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecomendacionesActivity extends AppCompatActivity implements FirebaseHelper.OnDataLoadedListener{

    //Variables
    private Button btnVolver;
    private RecyclerView recyclerView;
    private RecomendacionAdapter adapter;
    private List<Recomendacion> recomendacionesList;
    private FirebaseHelper firebaseHelper;
    private MutableLiveData<String> error = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recomendacionesList = new ArrayList<>();
        adapter = new RecomendacionAdapter(recomendacionesList);
        recyclerView.setAdapter(adapter);

        //Inicializa FirebaseHelper
        firebaseHelper = new FirebaseHelper(this);
        fetchRecomendaciones();

        //Botón para volver al MainActivity
        btnVolver = findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(v -> finish());
    }

    private void fetchRecomendaciones() {
        firebaseHelper.generarRecomendaciones();
    }

    @Override
    public void onRecomendacionesLoaded(List<Recomendacion> recomendaciones) {
        this.recomendacionesList.clear();
        this.recomendacionesList.addAll(recomendaciones);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDataLoaded(double consumoActual, double consumoMensual, double consumoAnual) {
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
    public void onHistorialLoaded(List<Historial> historialList) {
        //No hace falta implementar nada para esta actividad
    }

    @Override
    public void onError(String error) {
        this.error.postValue(error);
    }
}