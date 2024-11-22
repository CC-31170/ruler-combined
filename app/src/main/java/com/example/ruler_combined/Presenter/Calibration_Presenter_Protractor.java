package com.example.ruler_combined.Presenter;

import com.example.ruler_combined.Model.Calibration_Model_Protractor;
import com.example.ruler_combined.View.Calibration_Activity_Protractor;

public class Calibration_Presenter_Protractor {

    private Calibration_Activity_Protractor view;
    private Calibration_Model_Protractor model;

    public Calibration_Presenter_Protractor(Calibration_Activity_Protractor view) {
        this.view = view;
        this.model = new Calibration_Model_Protractor();
    }

    // 设置校准点的逻辑
    public void setCalibrationPoint(float x, float y) {
        model.setCalibrationPointX(x);
        model.setCalibrationPointY(y);
        // 进一步的处理逻辑，如通知 View 更新校准点的显示
    }

    public float[] getCalibrationPoint() {
        return new float[]{model.getCalibrationPointX(), model.getCalibrationPointY()};
    }
}
