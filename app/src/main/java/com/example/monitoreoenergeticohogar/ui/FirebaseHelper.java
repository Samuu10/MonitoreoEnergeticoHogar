package com.example.monitoreoenergeticohogar.ui;

import android.os.Handler;
import android.os.Looper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

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
                new Handler(Looper.getMainLooper()).post(() -> listener.onDataLoaded(consumoActual, consumoMensual));
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

    //Interfaz para manejar los datos cargados
    public interface OnDataLoadedListener {
        void onDataLoaded(double consumoActual, double consumoMensual);
        void onDataLoadedElectrodomesticos(HashMap<String, Double> consumoElectrodomesticos);
        void onDataLoadedHabitaciones(HashMap<String, Double> consumoHabitaciones);
        void onError(String error);
    }
}