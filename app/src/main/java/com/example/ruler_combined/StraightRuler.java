package com.example.ruler_combined;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;


public class StraightRuler extends AppCompatActivity {

    private TextView text;
    private NoEndlessRuler ruler;

    private NoEndlessRuler.OnRulerListener onRulerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_straight_ruler);
        Utils.initScreenSize(getApplicationContext());


        text = (TextView) findViewById(R.id.text);
        ruler = (NoEndlessRuler) findViewById(R.id.ruler);

        onRulerListener = new NoEndlessRuler.OnRulerListener() {
            @Override
            public void onValueChanged(int value) {
                text.setText(value+"");
            }
        };

        ruler.setRulerListener(onRulerListener);
    }
}