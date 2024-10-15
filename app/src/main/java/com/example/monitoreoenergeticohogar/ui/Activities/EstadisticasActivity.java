package com.example.monitoreoenergeticohogar.ui.Activities;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.monitoreoenergeticohogar.R;
import com.example.monitoreoenergeticohogar.ui.Estadisticas.CustomValueFormatter;
import com.example.monitoreoenergeticohogar.ui.Estadisticas.EstadisticasViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Clase para mostrar las estadísticas de consumo de energía de las habitaciones y electrodomésticos
public class EstadisticasActivity extends AppCompatActivity {

    //Variables
    private Button btnVolver;
    private EstadisticasViewModel viewModel;
    private PieChart pieChart;
    private BarChart barChart;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        //Inicializar graficos
        pieChart = findViewById(R.id.pie_chart);
        barChart = findViewById(R.id.bar_chart);

        //Configurar el gráfico circular
        pieChart.setBackgroundColor(getResources().getColor(android.R.color.white));
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelColor(getResources().getColor(android.R.color.black));
        pieChart.setEntryLabelTextSize(12f);
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);
        pieChart.getLegend().setEnabled(false);

        //Configurar el gráfico de barras
        barChart.setBackgroundColor(getResources().getColor(android.R.color.white));
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getXAxis().setTextSize(14f);
        barChart.getXAxis().setYOffset(10f);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getLegend().setEnabled(false);

        //Inicializar el ViewModel
        viewModel = new ViewModelProvider(this).get(EstadisticasViewModel.class);


        //Observamos los datos de consumo de habitaciones para actualizar el gráfico circular
        viewModel.getConsumoHabitaciones().observe(this, new Observer<HashMap<String, Double>>() {
            @Override
            public void onChanged(HashMap<String, Double> consumoHabitaciones) {
                actualizarGraficoCircular(consumoHabitaciones);
            }
        });

        //Observamos los datos de consumo de electrodomésticos para actualizar el gráfico de barras
        viewModel.getConsumoElectrodomesticos().observe(this, new Observer<HashMap<String, Double>>() {
            @Override
            public void onChanged(HashMap<String, Double> consumoElectrodomesticos) {
                actualizarGraficoBarras(consumoElectrodomesticos);
            }
        });

        //Botón para volver al MainActivity
        btnVolver = findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(v -> finish());
    }

    //Método para actualizar el gráfico circular
    private void actualizarGraficoCircular(HashMap<String, Double> consumoHabitaciones) {
        List<PieEntry> entries = new ArrayList<>();
        for (String key : consumoHabitaciones.keySet()) {
            entries.add(new PieEntry(consumoHabitaciones.get(key).floatValue(), key));
        }
        PieDataSet dataSet = new PieDataSet(entries, "Consumo Habitaciones");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(getResources().getColor(android.R.color.black));
        dataSet.setValueTextSize(12f);
        dataSet.setValueTypeface(Typeface.DEFAULT_BOLD);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    //Método para actualizar el gráfico de barras
    private void actualizarGraficoBarras(HashMap<String, Double> consumoElectrodomesticos) {
        List<BarEntry> entries = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        int index = 0;
        for (String key : consumoElectrodomesticos.keySet()) {
            entries.add(new BarEntry(index++, consumoElectrodomesticos.get(key).floatValue()));
            labels.add(key);
        }
        BarDataSet dataSet = new BarDataSet(entries, "Consumo Electrodomésticos");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(getResources().getColor(android.R.color.black));
        dataSet.setValueTextSize(12f);
        dataSet.setValueTypeface(Typeface.DEFAULT_BOLD);
        BarData data = new BarData(dataSet);
        data.setValueTextSize(12f);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);
        data.setValueFormatter(new CustomValueFormatter(labels));
        barChart.setData(data);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawLabels(false);
        xAxis.setDrawGridLines(false);
        barChart.setFitBars(true);
        barChart.invalidate();
    }
}