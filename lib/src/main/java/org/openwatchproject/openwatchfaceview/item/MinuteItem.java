package org.openwatchproject.openwatchfaceview.item;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.Calendar;
import java.util.List;

public class MinuteItem extends DrawableItem {
    public MinuteItem(int centerX, int centerY, List<Drawable> frames) {
        super(centerX, centerY, frames);
    }

    @Override
    public void draw(int viewCenterX, int viewCenterY, Canvas canvas, Calendar calendar, DataRepository dataRepository) {
        List<Drawable> drawables = getFrames();
        int minute = calendar.get(Calendar.MINUTE);
        Drawable drawable1 = drawables.get(minute / 10);
        Drawable drawable2 = drawables.get(minute % 10);

        draw(viewCenterX, viewCenterY, canvas, drawable1, drawable2);
    }
}
