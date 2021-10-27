package org.openwatchproject.openwatchfaceview.item;

import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.Calendar;
import java.util.List;

public class SecondRotatableItem extends RotatableItem {
    public SecondRotatableItem(int centerX, int centerY, List<Drawable> frames, float startAngle, float maxAngle, int direction) {
        super(centerX, centerY, frames, startAngle, maxAngle, direction);
    }

    @Override
    float getProgress(Calendar calendar, DataRepository dataRepository) {
        float analogSecond = (float) calendar.get(Calendar.SECOND)
                + ((float) calendar.get(Calendar.MILLISECOND) / 1000.0f);

        return analogSecond / 60.0f;
    }
}
