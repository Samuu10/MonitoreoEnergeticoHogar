package com.example.monitoreoenergeticohogar.ui.Activities;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.monitoreoenergeticohogar.R;

public class EstadisticasActivity extends AppCompatActivity {
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);



        //Botón para volver al MainActivity
        btnVolver = findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(v -> finish());
    }
}
