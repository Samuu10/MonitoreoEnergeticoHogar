package com.example.monitoreoenergeticohogar.ui;

import android.os.AsyncTask;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FirebaseAsyncTask extends AsyncTask <Void, Void, DataSnapshot> {

    //Variables
    private DatabaseReference databaseReference;
    private String childPath;
    private OnDataLoadedListener listener;
    private String errorMessage;

    //Constructor
    public FirebaseAsyncTask(DatabaseReference databaseReference, String childPath, OnDataLoadedListener listener) {
        this.databaseReference = databaseReference;
        this.childPath = childPath;
        this.listener = listener;
    }

    //Método para ejecutar la tarea de cargar los datos en segundo plano
    @Override
    protected DataSnapshot doInBackground(Void... voids) {
        //Variable para almacenar los datos de consumo
        final DataSnapshot[] dataSnapshot = new DataSnapshot[1];
        //Listener para obtener los datos de consumo de electrodomésticos
        databaseReference.child(childPath).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                dataSnapshot[0] = snapshot;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                errorMessage = databaseError.getMessage();
            }
        });

        //Esperar a que se complete la operación
        while (dataSnapshot[0] == null && errorMessage == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return dataSnapshot[0];
    }

    @Override
    protected void onPostExecute(DataSnapshot dataSnapshot) {
        if (dataSnapshot != null) {
            listener.onDataLoaded(dataSnapshot);
        } else {
            listener.onError(errorMessage);
        }
    }

    public interface OnDataLoadedListener {
        void onDataLoaded(DataSnapshot dataSnapshot);
        void onError(String error);
    }
}
