package org.openwatchproject.openwatchfaceview.item;

import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.List;
import java.util.Calendar;

public class SecondShadowRotatableItem extends RotatableItem {
    public SecondShadowRotatableItem(int centerX, int centerY, List<Drawable> frames, float startAngle, float maxAngle, int direction) {
        super(centerX, centerY, frames, startAngle, maxAngle, direction);
    }

    @Override
    float getProgress(Calendar calendar, DataRepository dataRepository) {
        float analogSecondShadow = (float) calendar.get(Calendar.SECOND)
                + ((float) calendar.get(Calendar.MILLISECOND) / 1000);

        return analogSecondShadow / 60.0f;
    }
}
