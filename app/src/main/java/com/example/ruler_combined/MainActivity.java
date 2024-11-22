package com.example.ruler_combined;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ruler_combined.View.Protractor_Activity;

public class MainActivity extends AppCompatActivity {
    Button StrRuler_bnt,Protractor_bnt,RollRuler_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        StrRuler_bnt=(Button)findViewById(R.id.str_btn) ;
        Protractor_bnt=(Button)findViewById(R.id.pro_btn);
        RollRuler_btn=(Button)findViewById(R.id.rol_btn) ;

        StrRuler_bnt.setOnClickListener(new View.OnClickListener() {//直尺界面
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Ruler_acceleration.class);
                startActivity(intent);
            }
        });

        Protractor_bnt.setOnClickListener(new View.OnClickListener() {//量角器界面
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Protractor_Activity.class);
                startActivity(intent);  //lyj add
            }
        });

        RollRuler_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),StraightRuler.class);
                startActivity(intent);
            }
        });


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}