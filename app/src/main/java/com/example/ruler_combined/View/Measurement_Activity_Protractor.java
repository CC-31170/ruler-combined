package com.example.ruler_combined.View;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ruler_combined.Presenter.Measurement_Presenter_Protractor;
import com.example.ruler_combined.R;

public class Measurement_Activity_Protractor extends AppCompatActivity {

    private Measurement_Presenter_Protractor presenter;
    private ImageView imageView;
    private TextView angleTextView;
    private float line1X, line1Y, line2X, line2Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);

        presenter = new Measurement_Presenter_Protractor(this);

        imageView = findViewById(R.id.capturedImageView);
        angleTextView = findViewById(R.id.angleTextView);

        // 显示传递过来的照片
        String photoPath = getIntent().getStringExtra("photoPath");
        if (photoPath != null) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(photoPath));
        }

        // 设置触摸监听器，允许用户拖动线条
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        line1X = event.getX();
                        line1Y = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        line2X = event.getX();
                        line2Y = event.getY();
                        float angle = presenter.calculateAngle(line1X, line1Y, line2X, line2Y);
                        updateAngleDisplay(angle);
                        break;
                }
                return true;
            }
        });
    }

    // 更新角度显示
    public void updateAngleDisplay(float angle) {
        angleTextView.setText("Angle: " + angle + "°");
    }
}
