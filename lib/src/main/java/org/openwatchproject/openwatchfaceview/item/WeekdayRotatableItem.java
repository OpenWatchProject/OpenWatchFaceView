package org.openwatchproject.openwatchfaceview.item;

import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.Calendar;
import java.util.List;

public class WeekdayRotatableItem extends RotatableItem {
    public WeekdayRotatableItem(int centerX, int centerY, List<Drawable> frames, float startAngle, float maxAngle, int direction) {
        super(centerX, centerY, frames, startAngle, maxAngle, direction);
    }

    @Override
    float getProgress(Calendar calendar, DataRepository dataRepository) {
        float analogWeekday = (float) (calendar.get(Calendar.DAY_OF_WEEK) - 2); // Sunday = -2, Monday = -1, Tuesday = 0, etc

        return analogWeekday / 7.0f;
    }
}
