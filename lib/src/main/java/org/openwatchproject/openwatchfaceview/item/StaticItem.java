package org.openwatchproject.openwatchfaceview.item;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.List;
import java.util.Calendar;

public class StaticItem extends AbstractItem {
    public StaticItem(int centerX, int centerY, List<Drawable> frames) {
        super(centerX, centerY, frames);
    }

    @Override
    public void draw(int viewCenterX, int viewCenterY, Canvas canvas, Calendar calendar, DataRepository dataRepository) {
        Drawable drawable = getFrame();
        int centerX = viewCenterX + this.centerX;
        int centerY = viewCenterY + this.centerY;
        int halfWidth = drawable.getIntrinsicWidth() / 2;
        int halfHeight = drawable.getIntrinsicHeight() / 2;

        drawable.setBounds(centerX - halfWidth, centerY - halfHeight, centerX + halfWidth, centerY + halfHeight);
        drawable.draw(canvas);
    }
}
