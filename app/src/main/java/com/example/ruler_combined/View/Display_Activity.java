package com.example.ruler_combined.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ruler_combined.R;

import java.io.File;

public class Display_Activity extends AppCompatActivity {

    private ImageView backgroundImageView;
    private Button retakeButton, circleButton, semiButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        backgroundImageView = findViewById(R.id.backgroundImageView);
        retakeButton = findViewById(R.id.retakeButton);
        circleButton = findViewById(R.id.circleButton);
        semiButton = findViewById(R.id.semiButton);

        // 获取传递的图片路径
        String photoPath = getIntent().getStringExtra("photoPath");
        if (photoPath != null) {
            File imgFile = new File(photoPath);
            if (imgFile.exists()) {
                // 加载图片并设置为 ImageView 的背景
                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                backgroundImageView.setImageBitmap(bitmap);
            }
        }

        // 设置重新拍照按钮点击事件
        retakeButton.setOnClickListener(v -> {
            Intent intent = new Intent(Display_Activity.this, Protractor_Activity.class);
            startActivity(intent);
            finish();
        });

        // 设置圆形量角器模式按钮点击事件
        circleButton.setOnClickListener(v -> proceedToCalibration("circle"));

        // 设置半圆形量角器模式按钮点击事件
        semiButton.setOnClickListener(v -> proceedToCalibration("semi"));
    }

    private void proceedToCalibration(String type) {
        Intent intent = new Intent(Display_Activity.this, Calibration_Activity_Protractor.class);
        intent.putExtra("photoPath", getIntent().getStringExtra("photoPath"));
        intent.putExtra("calibrationType", type);
        startActivity(intent);
    }
}
