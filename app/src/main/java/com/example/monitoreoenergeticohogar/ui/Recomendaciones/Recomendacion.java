package com.example.monitoreoenergeticohogar.ui.Recomendaciones;

//Clase que define una recomendación
public class Recomendacion {

    //Variables
    private String titulo;
    private String descripcion;

    //Constructor
    public Recomendacion(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    //Getters
    public String getTitulo() {
        return titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
}