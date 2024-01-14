package com.example.apksensor2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor  proximitySensor;
    private SensorEventListener proximitySensorListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor =sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (proximitySensor == null){
            Toast.makeText( this, "Proximity sensor is not Available", Toast.LENGTH_LONG).show();
            finish();
        }
        proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.values[0]< proximitySensor.getMaximumRange()){
                    getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                }else {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(proximitySensorListener, proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(proximitySensorListener);
    }

}