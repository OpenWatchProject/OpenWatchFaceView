package org.openwatchproject.openwatchfaceview.item;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.List;
import java.util.Calendar;
import java.util.List;

public class DayItem extends AbstractItem {
    public DayItem(int centerX, int centerY, List<Drawable> frames) {
        super(centerX, centerY, frames);
    }

    @Override
    public void draw(int viewCenterX, int viewCenterY, Canvas canvas, Calendar calendar, DataRepository dataRepository) {
        List<Drawable> drawables = getFrames();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Drawable drawable1 = drawables.get(day / 10);
        Drawable drawable2 = drawables.get(day % 10);

        int centerX = viewCenterX + this.centerX;
        int centerY = viewCenterY + this.centerY;
        int width = drawable1.getIntrinsicWidth();
        int halfHeight = drawable1.getIntrinsicHeight() / 2;
        int top = centerY - halfHeight;
        int bottom = centerY + halfHeight;
        drawable1.setBounds(centerX - width, top, centerX, bottom);
        drawable1.draw(canvas);
        drawable2.setBounds(centerX, top, centerX + width, bottom);
        drawable2.draw(canvas);
    }
}
