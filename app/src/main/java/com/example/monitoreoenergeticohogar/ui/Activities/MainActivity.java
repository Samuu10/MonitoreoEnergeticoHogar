package com.example.monitoreoenergeticohogar.ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitoreoenergeticohogar.R;
import com.example.monitoreoenergeticohogar.ui.Firebase.FirebaseHelper;
import com.example.monitoreoenergeticohogar.ui.Historial.ConsumoHistorial;

import java.util.HashMap;
import java.util.List;

//Actividad principal de la aplicación
public class MainActivity extends AppCompatActivity implements FirebaseHelper.OnDataLoadedListener {

    private TextView tvValorConsumoActual, tvValorConsumoMensual;
    private FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciasmos las tarjetas de consumo
        tvValorConsumoActual = findViewById(R.id.tv_valor_consumo_actual);
        tvValorConsumoMensual = findViewById(R.id.tv_valor_consumo_mensual);

        // Inicializa FirebaseHelper
        firebaseHelper = new FirebaseHelper(this);

        //Botón para navegar a la actividad de estadísticas
        findViewById(R.id.btn_estadisticas).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EstadisticasActivity.class);
            startActivity(intent);
        });

        //Botón para navegar a la actividad de análisis
        findViewById(R.id.btn_analisis).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AnalisisActivity.class);
            startActivity(intent);
        });

        //Botón para navegar a la actividad de historial
        findViewById(R.id.btn_historial).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistorialActivity.class);
            startActivity(intent);
        });

        //Cargamos los datos de consumo cuando la actividad se crea
        cargarDatosConsumo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Cargamos los datos de consumo cuando la actividad se reanuda
        cargarDatosConsumo();
    }

    //Método para cargar los datos de consumo desde Firebase
    private void cargarDatosConsumo() {
        firebaseHelper.cargarConsumoActual();
    }

    //Método para mostrar los datos de consumo en las tarjetas
    @Override
    public void onDataLoaded(double consumoActual, double consumoMensual) {
        tvValorConsumoActual.setText(consumoActual + " kW/h");
        tvValorConsumoMensual.setText(consumoMensual + " kW/h");
    }

    @Override
    public void onDataLoadedElectrodomesticos(HashMap<String, Double> consumoElectrodomesticos) {
        //No hace falta implementar nada para esta actividad
    }

    @Override
    public void onDataLoadedHabitaciones(HashMap<String, Double> consumoHabitaciones) {
        //No hace falta implementar nada en esta actividad
    }

    @Override
    public void onHistorialLoaded(List<ConsumoHistorial> historialList) {
        //No hace falta implementar nada en esta actividad
    }

    //Método para mostrar un mensaje en caso de error
    @Override
    public void onError(String error) {
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}