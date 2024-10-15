package com.example.monitoreoenergeticohogar.ui.Estadisticas;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.util.List;

//Clase para customizar el formato de los valores de las barras en el gráfico de barras
public class CustomValueFormatter extends ValueFormatter {

    //Variables
    private final List<String> labels;

    //Constructor
    public CustomValueFormatter(List<String> labels) {
        this.labels = labels;
    }

    //Método para obtener el valor de la barra
    @Override
    public String getBarLabel(BarEntry barEntry) {
        int index = (int) barEntry.getX();
        return labels.get(index) + ": " + barEntry.getY();
    }
}