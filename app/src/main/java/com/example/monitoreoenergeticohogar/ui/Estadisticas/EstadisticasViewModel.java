package com.example.monitoreoenergeticohogar.ui.Estadisticas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.monitoreoenergeticohogar.ui.Firebase.FirebaseHelper;
import com.example.monitoreoenergeticohogar.ui.Historial.ConsumoHistorial;

import java.util.HashMap;
import java.util.List;

//Clase para visualizar los datos de consumo de electrodomésticos y habitaciones
public class EstadisticasViewModel extends ViewModel implements FirebaseHelper.OnDataLoadedListener {

    //Variables
    private FirebaseHelper firebaseHelper;
    private MutableLiveData<HashMap<String, Double>> electrodomesticosConsumo = new MutableLiveData<>();
    private MutableLiveData<HashMap<String, Double>> habitacionesConsumo = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    //Constructor
    public EstadisticasViewModel() {
        firebaseHelper = new FirebaseHelper(this);
        cargarDatosConsumo();
    }

    // Método para cargar datos de consumo desde Firebase
    public void cargarDatosConsumo() {
        firebaseHelper.cargarConsumoElectrodomesticos();
        firebaseHelper.cargarConsumoHabitaciones();
    }

    //Getter para obtener los datos de consumo de electrodomésticos
    public LiveData<HashMap<String, Double>> getConsumoElectrodomesticos() {
        return electrodomesticosConsumo;
    }

    //Getter para obtener los datos de consumo de habitaciones
    public LiveData<HashMap<String, Double>> getConsumoHabitaciones() {
        return habitacionesConsumo;
    }

    @Override
    public void onDataLoadedElectrodomesticos(HashMap<String, Double> consumoElectrodomesticos) {
        electrodomesticosConsumo.postValue(consumoElectrodomesticos);
    }

    @Override
    public void onDataLoadedHabitaciones(HashMap<String, Double> consumoHabitaciones) {
        habitacionesConsumo.postValue(consumoHabitaciones);
    }

    @Override
    public void onDataLoaded(double consumoActual, double consumoMensual) {
        //No hace falta implementar nada para esta actividad
    }

    @Override
    public void onHistorialLoaded(List<ConsumoHistorial> historialList) {
        //No hace falta implementar nada para esta actividad
    }

    @Override
    public void onError(String error) {
        this.error.postValue(error);
    }
}