package org.openwatchproject.openwatchfaceview.item;

import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.Calendar;
import java.util.List;

public class HourRotatableItem extends RotatableItem {
    public HourRotatableItem(int centerX, int centerY, List<Drawable> frames, float startAngle, float maxAngle, int direction) {
        super(centerX, centerY, frames, startAngle, maxAngle, direction);
    }

    @Override
    float getProgress(Calendar calendar, DataRepository dataRepository) {
        float analogHour = (float) calendar.get(Calendar.HOUR)
                + ((float) calendar.get(Calendar.MINUTE) / 60.0f)
                + ((float) calendar.get(Calendar.SECOND) / 60.0f / 60.0f);

        return analogHour / 12.0f;
    }
}
