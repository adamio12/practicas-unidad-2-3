package com.example.eva3_1_sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lstVwSen;
    String[] asSensores;
    SensorManager sensores;
    List<Sensor> lstSensores;
    TextView txtVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstVwSen = findViewById(R.id.lstVwSen);
        txtVw = findViewById(R.id.txtVwL);
        sensores = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        lstSensores = sensores.getSensorList(Sensor.TYPE_ALL);
        asSensores = new String[lstSensores.size()];
        int i=0;
        for(Sensor sensor: lstSensores){
            asSensores[i]= sensor.getName();
            i++;
        }
        lstVwSen.setAdapter(new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                asSensores
        ));

        lstVwSen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                Sensor sensor = lstSensores.get(posicion);
                txtVw.setText("Rango máximo " + sensor.getMaximumRange()+"\n"+
                        "Demora: " + sensor.getMaxDelay() + "\n"+
                        "Consumo energía: " + sensor.getPower() + "\n"+
                        "Precisión: "+ sensor.getResolution() + "\n");
            }
        });
    }
}
