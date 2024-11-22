package com.example.ruler_combined.Presenter;

import android.content.Intent;

import com.example.ruler_combined.Model.Protractor_Model;
import com.example.ruler_combined.View.Calibration_Activity_Protractor;
import com.example.ruler_combined.View.Protractor_Activity;

public class Protractor_Presenter {

    private Protractor_Activity view;
    private Protractor_Model model;

    public Protractor_Presenter(Protractor_Activity view) {
        this.view = view;
        this.model = new Protractor_Model();
    }

    // 设置量角器的模式（180° 或 360°）
    public void setMode(String mode) {
        model.setMode(mode);
    }

    public String getMode() {
        return model.getMode();
    }

    // 启动校准活动并传递模式
    public void startCalibration(String photoPath) {
        Intent intent = new Intent(view, Calibration_Activity_Protractor.class);
        intent.putExtra("photoPath", photoPath);
        intent.putExtra("mode", model.getMode()); // 传递模式
        view.startActivity(intent);
    }
}
