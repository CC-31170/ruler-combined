package com.example.ruler_combined;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ruler_acceleration extends AppCompatActivity {

    private SensorManager sensorManager;
    private boolean isMeasuring = false;
    private long startTime;
    private float velocityX = 0.0f;
    private float velocityY = 0.0f;
    private float velocityZ = 0.0f;
    private float distance = 0.0f;
    TextView x_tv,y_tv,z_tv,v_tv,d_tv;
    EditText x_et,y_et,z_et,v_et,d_et;
    ImageButton str_btn,end_btn;
    Sensor mSensor;
    private SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.function_ruler_acceleration);

        x_tv=(TextView)findViewById(R.id.x_tv);
        y_tv=(TextView)findViewById(R.id.y_tv);
        z_tv=(TextView)findViewById(R.id.z_tv);
        v_tv=(TextView)findViewById(R.id.v_tv);
        d_tv=(TextView)findViewById(R.id.d_tv);
        x_et=(EditText)findViewById(R.id.x_et);
        y_et=(EditText)findViewById(R.id.y_et);
        z_et=(EditText)findViewById(R.id.z_et);
        v_et=(EditText)findViewById(R.id.v_et);
        d_et=(EditText)findViewById(R.id.d_et);
        str_btn=(ImageButton)findViewById(R.id.start_btn);
        end_btn=(ImageButton)findViewById(R.id.end_btn);

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);


        if (mSensor == null) {
            Toast.makeText(this, "Your device does not support linear accelerometer", Toast.LENGTH_SHORT).show();
            return;
        }

//        str_btn.setOnClickListener(v->startMeasuring());
//        end_btn.setOnClickListener(v->stopMeasuring());

        str_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isMeasuring) {
                    isMeasuring = true;
                    startTime = SystemClock.elapsedRealtime();
                    velocityX = 0.0f;
                    velocityY=0.0f;
                    velocityZ=0.0f;
                    distance = 0.0f;
                    sensorManager.registerListener(sensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
                }
            }
        });

        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMeasuring) {
                    isMeasuring = false;
                    sensorManager.unregisterListener(sensorEventListener);
                    d_et.setText(String.format("Distance: %.2f m", distance));
                }
            }
        });




//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

//    private void startMeasuring() {
//        if (!isMeasuring) {
//            isMeasuring = true;
//            startTime = SystemClock.elapsedRealtime();
//            velocity = 0.0f;
//            distance = 0.0f;
//            boolean isRegistered=sensorManager.registerListener(this, linearAccelerometer, SensorManager.SENSOR_DELAY_GAME);
//            if (!isRegistered) {
//                Toast.makeText(this, "Failed to register sensor listener", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void stopMeasuring() {
//        if (isMeasuring) {
//            isMeasuring = false;
//            sensorManager.unregisterListener(this);
//            d_et.setText(String.format("Distance: %.2f m", distance));
//        }
//    }


//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        if (true) {
//
//            x_et.setText(String.valueOf(event.values[0]));
//            y_et.setText(String.valueOf(event.values[1]));
//            z_et.setText(String.valueOf(event.values[2]));
//
//            float ax = event.values[0];
//            float ay = event.values[1];
//            float az = event.values[2];
//
//            long currentTime = SystemClock.elapsedRealtime();
//            float deltaTime = (currentTime - startTime) / 1000.0f;
//            startTime = currentTime;
//
//            float acceleration = (float) Math.sqrt(ax * ax + ay * ay + az * az);
//            velocity += acceleration * deltaTime;
//            distance += velocity * deltaTime;
//
//            v_et.setText(String.valueOf(velocity));
//            d_et.setText(String.valueOf(distance));
//        }
//    }

//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        // Do nothing
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (isMeasuring) {
//            sensorManager.unregisterListener(this);
//        }
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (isMeasuring) {
//            sensorManager.registerListener(sensorEventListener, linearAccelerometer, SensorManager.SENSOR_DELAY_GAME);
//        }
//    }

    @Override
    protected void onResume(){
        super.onResume();
        if (isMeasuring) {
            sensorManager.registerListener(sensorEventListener, mSensor, SensorManager.SENSOR_DELAY_GAME);
        }
        sensorEventListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (isMeasuring) {

                    x_et.setText(String.valueOf(sensorEvent.values[0]));
                    y_et.setText(String.valueOf(sensorEvent.values[1]));
                    z_et.setText(String.valueOf(sensorEvent.values[2]));

                    float ay = sensorEvent.values[1];

                    long currentTime = SystemClock.elapsedRealtime();
                    float deltaTime = (currentTime - startTime) / 1000.0f;
                    startTime = currentTime;

                    // 更新y方向上的速度
                    velocityY += ay * deltaTime;

                    // 更新y方向上的距离
                    distance += velocityY * deltaTime;

                    // 更新距离
                    distance += velocityY * deltaTime;

//                    float ax = sensorEvent.values[0];
//                    float ay = sensorEvent.values[1];
//                    float az = sensorEvent.values[2];
//
//                    long currentTime = SystemClock.elapsedRealtime();
//                    float deltaTime = (currentTime - startTime) / 1000.0f;
//                    startTime = currentTime;
//
//                    float acceleration = (float) Math.sqrt(ax * ax + ay * ay + az * az);
//                    //velocity += acceleration * deltaTime;
//                    if (acceleration < 0.05) {
//                        velocity = 0.0f;
//                    } else {
//                        velocity += acceleration * deltaTime;
//                    }
//
//                    distance += velocity * deltaTime;

                    v_et.setText(String.valueOf(velocityY));
                    d_et.setText(String.valueOf(distance));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(sensorEventListener,mSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (isMeasuring) {
            sensorManager.registerListener(sensorEventListener, mSensor, SensorManager.SENSOR_DELAY_GAME);
        }
        //sensorManager.unregisterListener(sensorEventListener);
    }


//    @Override
//    protected void onResume(){
//        super.onResume();
//        sensorEventListener= new SensorEventListener() {
//            @Override
//            public void onSensorChanged(SensorEvent sensorEvent) {
//                x_et.setText(String.valueOf(sensorEvent.values[0]));
//                y_et.setText(String.valueOf(sensorEvent.values[1]));
//                z_et.setText(String.valueOf(sensorEvent.values[2]));
//            }
//
//            @Override
//            public void onAccuracyChanged(Sensor sensor, int i) {
//
//            }
//        };
//        sensorManager.registerListener(sensorEventListener,mSensor,SensorManager.SENSOR_DELAY_FASTEST);
//    }
//
//    @Override
//    protected void onPause(){
//        super.onPause();
//        sensorManager.unregisterListener(sensorEventListener);
//    }





}