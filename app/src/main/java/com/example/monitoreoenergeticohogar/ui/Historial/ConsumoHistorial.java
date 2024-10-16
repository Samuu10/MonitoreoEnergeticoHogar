package com.example.monitoreoenergeticohogar.ui.Historial;

//Clase para almacenar el historial de consumo de energía del hogar
public class ConsumoHistorial {

    //Variables
    private String mes;
    private double consumo;
    private double precio;

    //Constructor
    public ConsumoHistorial(String mes, double consumo, double precio) {
        this.mes = mes;
        this.consumo = consumo;
        this.precio = precio;
    }

    //Getters
    public String getMes() {
        return mes;
    }
    public double getConsumo() {
        return consumo;
    }
    public double getPrecio() {
        return precio;
    }
}