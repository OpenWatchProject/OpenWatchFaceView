package org.openwatchproject.openwatchfaceview.item;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.Calendar;
import java.util.List;

public class SecondItem extends DrawableItem {
    public SecondItem(int centerX, int centerY, List<Drawable> frames) {
        super(centerX, centerY, frames);
    }

    @Override
    public void draw(int viewCenterX, int viewCenterY, Canvas canvas, Calendar calendar, DataRepository dataRepository) {
        List<Drawable> drawables = getFrames();
        int second = calendar.get(Calendar.SECOND);
        Drawable drawable1 = drawables.get(second / 10);
        Drawable drawable2 = drawables.get(second % 10);

        draw(viewCenterX, viewCenterY, canvas, drawable1, drawable2);
    }
}
