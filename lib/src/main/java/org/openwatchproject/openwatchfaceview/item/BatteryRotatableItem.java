package org.openwatchproject.openwatchfaceview.item;

import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.List;
import java.util.Calendar;

public class BatteryRotatableItem extends RotatableItem {
    public BatteryRotatableItem(int centerX, int centerY, List<Drawable> frames, float startAngle, float maxAngle, int direction) {
        super(centerX, centerY, frames, startAngle, maxAngle, direction);
    }

    @Override
    float getProgress(Calendar calendar, DataRepository dataRepository) {
        float analogBattery = (float) dataRepository.getBatteryPercentage();

        return analogBattery / 100.0f;
    }
}
