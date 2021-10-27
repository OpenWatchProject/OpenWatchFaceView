package org.openwatchproject.openwatchfaceview.item;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.Calendar;
import java.util.List;

public class HourItem extends DrawableItem {
    public HourItem(int centerX, int centerY, List<Drawable> frames) {
        super(centerX, centerY, frames);
    }

    @Override
    public void draw(int viewCenterX, int viewCenterY, Canvas canvas, Calendar calendar, DataRepository dataRepository) {
        List<Drawable> drawables = getFrames();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        /*if (!DateFormat.is24HourFormat(context)) { // TODO: Fix this
            hour = hour % 12;
            if (hour == 0) {
                hour = 12;
            }
        }*/
        Drawable drawable1 = drawables.get(hour / 10);
        Drawable drawable2 = drawables.get(hour % 10);
        draw(viewCenterX, viewCenterY, canvas, drawable1, drawable2);
    }
}
