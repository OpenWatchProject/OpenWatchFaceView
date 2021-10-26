package org.openwatchproject.openwatchfaceview.item;

import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.List;
import java.util.Calendar;

public class MonthRotatableItem extends RotatableItem {
    public MonthRotatableItem(int centerX, int centerY, List<Drawable> frames, float startAngle, float maxAngle, int direction) {
        super(centerX, centerY, frames, startAngle, maxAngle, direction);
    }

    @Override
    float getProgress(Calendar calendar, DataRepository dataRepository) {
        float analogMonth = (float) (calendar.get(Calendar.MONTH) + 1); // Calendar.MONTH starts at 0

        return analogMonth / 12.0f;
    }
}
