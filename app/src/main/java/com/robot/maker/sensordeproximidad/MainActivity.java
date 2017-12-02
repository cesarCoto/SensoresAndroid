package com.robot.maker.sensordeproximidad;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView textView;
    Switch aSwitch;
    ImageView imageView;

    SensorManager sensorManager;
    Sensor sensorProximidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.texto);
        aSwitch = findViewById(R.id.switch1);
        imageView = findViewById(R.id.imagen);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(sensorProximidad == null){
            Toast.makeText(this, "No cuentas con sensor de proximidad", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            aSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    accion();
                }
            });
        }
    }
    private void accion(){
        if (aSwitch.isChecked()) {
            aSwitch.setText(getString(R.string.apagar_sensor));
            textView.setVisibility(View.VISIBLE);
            sensorManager.registerListener(this,sensorProximidad,SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            aSwitch.setText(getString(R.string.encender_sensor));
            textView.setVisibility(View.GONE);
            sensorManager.unregisterListener(this,sensorProximidad);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values [0] == 0){
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
