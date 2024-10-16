package com.example.monitoreoenergeticohogar.ui.Historial;

//Clase que define un registro de historial de consumo
public class Historial {

    //Variables
    private String mes;
    private double consumo;
    private double precio;

    //Constructor
    public Historial(String mes, double consumo, double precio) {
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