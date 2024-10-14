package com.example.monitoreoenergeticohogar.ui;

import android.os.Handler;
import android.os.Looper;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

public class FirebaseHelper {
    //Variables
    private DatabaseReference databaseReference;
    private OnDataLoadedListener listener;

    //Constructor
    public FirebaseHelper(OnDataLoadedListener listener) {
        this.listener = listener;
        databaseReference = FirebaseDatabase.getInstance().getReference("consumo");
    }

    //Método para cargar los datos de consumo actual
    public void cargarConsumoActual() {
        //Listener para obtener el valor de consumo actual
        databaseReference.child("actual").child("valor").addListenerForSingleValueEvent(new ValueEventListener() {

            //Método para obtener el valor de consumo actual y pasarselo a cargarConsumoMensual
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Cogemos el valor de consumo actual y lo pasamos a double
                double consumoActual = dataSnapshot.getValue(Double.class);
                //Llamamos al método para cargar el consumo mensual
                cargarConsumoMensual(consumoActual);
            }

            //Método para mostrar un mensaje en caso de error
            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });
    }

    //Método para cargar los datos de consumo mensual
    private void cargarConsumoMensual(double consumoActual) {
        //Listener para obtener el valor de consumo mensual
        databaseReference.child("mensual").child("valor").addListenerForSingleValueEvent(new ValueEventListener() {

            //Método para obtener el valor de consumo mensual y pasarselo a onDataLoaded
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Cogemos el valor de consumo mensual y lo pasamos a double
                double consumoMensual = dataSnapshot.getValue(Double.class);
                //Creamos un hadler, para que se ejecute en el hilo principal
                new Handler(Looper.getMainLooper()).post(() -> listener.onDataLoaded(consumoActual, consumoMensual));
            }

            //Método para mostrar un mensaje en caso de error
            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });
    }

    //Interfaz para el listener de los datos
    public interface OnDataLoadedListener {
        void onDataLoaded(double consumoActual, double consumoMensual);
        void onError(String error);
    }
}