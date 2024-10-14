package com.example.monitoreoenergeticohogar.ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.monitoreoenergeticohogar.R;


public class MainActivity extends AppCompatActivity {

    private TextView tvValorConsumoActual, tvValorConsumoMensual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a las tarjetas de consumo
        tvValorConsumoActual = findViewById(R.id.tv_valor_consumo_actual);
        tvValorConsumoMensual = findViewById(R.id.tv_valor_consumo_mensual);

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

        // Simulación de la carga de datos de consumo (puedes cargar esto desde Firebase)
        cargarDatosConsumo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarDatosConsumo();
    }

    // Método para cargar los datos de consumo (en este ejemplo es fijo, pero puede venir de Firebase)
    private void cargarDatosConsumo() {
        // Datos simulados
        double consumoActual = 23.5; // kWh
        double consumoMensual = 150.75; // kWh

        // Mostrar los datos en las tarjetas
        tvValorConsumoActual.setText(consumoActual + " kWh");
        tvValorConsumoMensual.setText(consumoMensual + " kWh");
    }
}
