package com.example.ruler_combined.Presenter;

import com.example.ruler_combined.Model.Measurement_Model_Protractor;
import com.example.ruler_combined.View.Measurement_Activity_Protractor;

public class Measurement_Presenter_Protractor {

    private Measurement_Activity_Protractor view;
    private Measurement_Model_Protractor model;

    public Measurement_Presenter_Protractor(Measurement_Activity_Protractor view) {
        this.view = view;
        this.model = new Measurement_Model_Protractor();
    }

    // 计算两个点之间的角度
    public float calculateAngle(float x1, float y1, float x2, float y2) {
        float deltaX = x2 - x1;
        float deltaY = y2 - y1;
        double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
        if (angle < 0) {
            angle += 360;
        }
        model.setAngle((float) angle);
        return model.getAngle();
    }
}
