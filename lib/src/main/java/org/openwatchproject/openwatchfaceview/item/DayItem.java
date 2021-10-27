package org.openwatchproject.openwatchfaceview.item;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.Calendar;
import java.util.List;

public class DayItem extends DrawableItem {
    public DayItem(int centerX, int centerY, List<Drawable> frames) {
        super(centerX, centerY, frames);
    }

    @Override
    public void draw(int viewCenterX, int viewCenterY, Canvas canvas, Calendar calendar, DataRepository dataRepository) {
        List<Drawable> drawables = getFrames();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Drawable drawable1 = drawables.get(day / 10);
        Drawable drawable2 = drawables.get(day % 10);

        draw(viewCenterX, viewCenterY, canvas, drawable1, drawable2);
    }
}
