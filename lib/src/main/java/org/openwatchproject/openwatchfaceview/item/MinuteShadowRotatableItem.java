package org.openwatchproject.openwatchfaceview.item;

import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.Calendar;
import java.util.List;

public class MinuteShadowRotatableItem extends RotatableItem {
    public MinuteShadowRotatableItem(int centerX, int centerY, List<Drawable> frames, float startAngle, float maxAngle, int direction) {
        super(centerX, centerY, frames, startAngle, maxAngle, direction);
    }

    @Override
    float getProgress(Calendar calendar, DataRepository dataRepository) {
        float analogMinuteShadow = (float) calendar.get(Calendar.MINUTE)
                + ((float) calendar.get(Calendar.SECOND) / 60.0f);

        return analogMinuteShadow / 60.0f;
    }
}
