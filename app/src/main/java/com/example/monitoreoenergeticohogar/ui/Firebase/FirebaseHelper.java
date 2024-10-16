package com.example.monitoreoenergeticohogar.ui.Firebase;

import android.os.Handler;
import android.os.Looper;

import com.example.monitoreoenergeticohogar.ui.Historial.Historial;
import com.example.monitoreoenergeticohogar.ui.Recomendaciones.Recomendacion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Clase para manejar las operaciones de datos con Firebase
public class FirebaseHelper {

    //Variables
    private DatabaseReference databaseReference;
    private OnDataLoadedListener listener;

    //Constructor
    public FirebaseHelper(OnDataLoadedListener listener) {
        this.listener = listener;
        databaseReference = FirebaseDatabase.getInstance().getReference("consumo");
    }

    //Método para cargar los datos de consumo actual en segundo plano
    public void cargarConsumoActual() {
        new FirebaseAsyncTask(databaseReference, "actual/valor", new FirebaseAsyncTask.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(DataSnapshot dataSnapshot) {
                double consumoActual = dataSnapshot.getValue(Double.class);
                cargarConsumoMensual(consumoActual);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        }).execute();
    }

    //Método para cargar los datos de consumo mensual en segundo plano
    private void cargarConsumoMensual(double consumoActual) {
        new FirebaseAsyncTask(databaseReference, "mensual/valor", new FirebaseAsyncTask.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(DataSnapshot dataSnapshot) {
                double consumoMensual = dataSnapshot.getValue(Double.class);
                cargarConsumoAnual(consumoActual, consumoMensual);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        }).execute();
    }

    //Método para cargar los datos de consumo anual en segundo plano
    private void cargarConsumoAnual(double consumoActual, double consumoMensual) {
        new FirebaseAsyncTask(databaseReference, "anual/valor", new FirebaseAsyncTask.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(DataSnapshot dataSnapshot) {
                double consumoAnual = dataSnapshot.getValue(Double.class);
                new Handler(Looper.getMainLooper()).post(() -> listener.onDataLoaded(consumoActual, consumoMensual, consumoAnual));
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        }).execute();
    }

    //Método para cargar los datos de consumo de electrodomésticos en segundo plano
    public void cargarConsumoElectrodomesticos() {
        new FirebaseAsyncTask(databaseReference, "electrodomesticos", new FirebaseAsyncTask.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(DataSnapshot dataSnapshot) {
                HashMap<String, Double> consumoElectrodomesticos = new HashMap<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String nombre = snapshot.getKey();
                    Double consumo = snapshot.getValue(Double.class);
                    consumoElectrodomesticos.put(nombre, consumo);
                }
                new Handler(Looper.getMainLooper()).post(() -> listener.onDataLoadedElectrodomesticos(consumoElectrodomesticos));
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        }).execute();
    }

    //Método para cargar los datos de consumo de habitaciones en segundo plano
    public void cargarConsumoHabitaciones() {
        new FirebaseAsyncTask(databaseReference, "habitaciones", new FirebaseAsyncTask.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(DataSnapshot dataSnapshot) {
                HashMap<String, Double> consumoHabitaciones = new HashMap<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String habitacion = snapshot.getKey();
                    Double consumo = snapshot.getValue(Double.class);
                    consumoHabitaciones.put(habitacion, consumo);
                }
                new Handler(Looper.getMainLooper()).post(() -> listener.onDataLoadedHabitaciones(consumoHabitaciones));
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        }).execute();
    }

    //Método para cargar el historial de consumo en segundo plano
    public void cargarHistorialConsumo() {
        new FirebaseAsyncTask(databaseReference, "historial", new FirebaseAsyncTask.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(DataSnapshot dataSnapshot) {
                List<Historial> historialList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String mes = snapshot.child("mes").getValue(String.class);
                    double consumo = snapshot.child("consumo").getValue(Double.class);
                    double precio = snapshot.child("precio").getValue(Double.class);
                    historialList.add(new Historial(mes, consumo, precio));
                }
                new Handler(Looper.getMainLooper()).post(() -> listener.onHistorialLoaded(historialList));
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        }).execute();
    }

    //Método para cargar recomendaciones en segundo plano
    public void generarRecomendaciones() {
        new FirebaseAsyncTask(databaseReference, "recomendaciones", new FirebaseAsyncTask.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(DataSnapshot dataSnapshot) {
                List<Recomendacion> recomendaciones = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String titulo = snapshot.child("titulo").getValue(String.class);
                    String descripcion = snapshot.child("descripcion").getValue(String.class);
                    recomendaciones.add(new Recomendacion(titulo, descripcion));
                }
                new Handler(Looper.getMainLooper()).post(() -> listener.onRecomendacionesLoaded(recomendaciones));
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        }).execute();
    }

    //Interfaz para manejar los datos cargados
    public interface OnDataLoadedListener {
        void onDataLoaded(double consumoActual, double consumoMensual, double consumoAnual);
        void onDataLoadedElectrodomesticos(HashMap<String, Double> consumoElectrodomesticos);
        void onDataLoadedHabitaciones(HashMap<String, Double> consumoHabitaciones);
        void onHistorialLoaded(List<Historial> historialList);
        void onRecomendacionesLoaded(List<Recomendacion> recomendacionesList);
        void onError(String error);
    }
}