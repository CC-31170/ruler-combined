package com.example.ruler_combined.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ruler_combined.Presenter.Calibration_Presenter_Protractor;
import com.example.ruler_combined.R;

public class Calibration_Activity_Protractor extends AppCompatActivity {

    private Calibration_Presenter_Protractor presenter;
    private ImageView imageView;
    private Bitmap originalBitmap, overlayBitmap;
    private boolean isCircleMode; // true: 显示圆盘，false: 显示半圆盘

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);

        presenter = new Calibration_Presenter_Protractor(this);
        imageView = findViewById(R.id.capturedImageView);

        // 获取传递过来的照片路径
        String photoPath = getIntent().getStringExtra("photoPath");
        isCircleMode = getIntent().getBooleanExtra("isCircleMode", true); // true 为圆盘，false 为半圆盘

        if (photoPath != null) {
            originalBitmap = BitmapFactory.decodeFile(photoPath);
            overlayBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
            imageView.setImageBitmap(overlayBitmap);
            imageView.setScaleType(ImageView.ScaleType.CENTER); // 确保图片居中显示
        } else {
            Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
        }

        // 设置触摸监听器，将触摸点作为圆心点，并在该位置绘制量角器
        imageView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float touchX = event.getX();
                float touchY = event.getY();
                presenter.setCalibrationPoint(touchX, touchY);
                Toast.makeText(this, "Calibration point set at: " + touchX + ", " + touchY, Toast.LENGTH_SHORT).show();

                // 将量角器圆心对齐到用户触摸的位置
                showProtractorOverlay(touchX, touchY);
            }
            return true;
        });
    }

    private void showProtractorOverlay(float centerX, float centerY) {
        Bitmap protractorBitmap;

        // 根据模式加载圆盘或半圆盘图像
        if (isCircleMode) {
            protractorBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        } else {
            protractorBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.semicircle);
        }

        // 创建一个新的 Bitmap 作为叠加层
        overlayBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(overlayBitmap);
        Paint paint = new Paint();

        // 计算绘制位置，以确保图片的中心点与触摸点重合
        float drawX = centerX - (protractorBitmap.getWidth() / 2.0f);
        float drawY = centerY - (protractorBitmap.getHeight() / 2.0f);

        // 在计算的位置绘制图片
        canvas.drawBitmap(protractorBitmap, drawX, drawY, paint);

        // 更新图像视图，确保叠加层在最上层
        imageView.setImageBitmap(overlayBitmap);
    }
}
