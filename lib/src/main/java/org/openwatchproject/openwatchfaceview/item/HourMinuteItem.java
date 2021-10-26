package org.openwatchproject.openwatchfaceview.item;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.List;
import java.util.Calendar;
import java.util.List;

public class HourMinuteItem extends AbstractItem {
    public HourMinuteItem(int centerX, int centerY, List<Drawable> frames) {
        super(centerX, centerY, frames);
    }

    @Override
    public void draw(int viewCenterX, int viewCenterY, Canvas canvas, Calendar calendar, DataRepository dataRepository) {
        List<Drawable> drawables = getFrames();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        Drawable periodIndicator = null;
        /*if (!DateFormat.is24HourFormat(context)) { // TODO: Fix this
            if (drawables.size() > 11) {
                if (hour < 12) {
                    periodIndicator = drawables.get(11);
                } else {
                    periodIndicator = drawables.get(12);
                }
            }

            hour = hour % 12;
            if (hour == 0) {
                hour = 12;
            }
        }*/
        Drawable h10 = drawables.get(hour / 10);
        Drawable h1 = drawables.get(hour % 10);
        Drawable separator = drawables.get(10);
        Drawable m10 = drawables.get(minute / 10);
        Drawable m1 = drawables.get(minute % 10);

        int centerX = viewCenterX + this.centerX;
        int centerY = viewCenterY + this.centerY;
        int numberWidth = h10.getIntrinsicWidth();
        int numberHalfHeight = h10.getIntrinsicHeight() / 2;
        int separatorWidth = separator.getIntrinsicWidth();
        int periodWidth = 0;
        if (periodIndicator != null) {
            periodWidth = periodIndicator.getIntrinsicWidth();
        }
        int startX = centerX - ((((numberWidth * 4) + separatorWidth) + periodWidth) / 2);

        int top = centerY - numberHalfHeight;
        int bottom = centerY + numberHalfHeight;

        h10.setBounds(startX, top, startX + numberWidth, bottom);
        h10.draw(canvas);
        h1.setBounds(startX + numberWidth, top, (numberWidth * 2) + startX, bottom);
        h1.draw(canvas);
        if (calendar.get(Calendar.SECOND) % 2 == 0) {
            separator.setBounds((numberWidth * 2) + startX, top, (numberWidth * 2) + startX + separatorWidth, bottom);
            separator.draw(canvas);
        }
        m10.setBounds((numberWidth * 2) + startX + separatorWidth, top, (numberWidth * 3) + startX + separatorWidth, bottom);
        m10.draw(canvas);
        m1.setBounds((numberWidth * 3) + startX + separatorWidth, top, (numberWidth * 4) + startX + separatorWidth, bottom);
        m1.draw(canvas);
        if (periodIndicator != null) {
            periodIndicator.setBounds((numberWidth * 4) + startX + separatorWidth, top, (numberWidth * 4) + startX + separatorWidth + periodWidth, bottom);
            periodIndicator.draw(canvas);
        }
    }
}
