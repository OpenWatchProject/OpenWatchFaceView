package org.openwatchproject.openwatchfaceview.item;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.openwatchproject.openwatchfaceview.DataRepository;
import org.openwatchproject.openwatchfaceview.OpenWatchFaceConstants;

import java.util.Calendar;
import java.util.List;

public abstract class RotatableItem extends DrawableItem {
    private static final String TAG = "RotatableItem";

    /**
     * Indicates the angle which the item will start at.
     * Default: 0.0
     */
    final float startAngle;

    /**
     * Indicates the maximum angle which the item will be at, starting from "startAngle".
     * Default: 360.0
     */
    final float maxAngle;

    /**
     * Indicates the direction for the analog hand.
     * 0 = Clockwise
     * 1 = Counterclockwise
     * Default: 0
     */
    final int direction;

    public RotatableItem(int centerX, int centerY, List<Drawable> frames, float startAngle, float maxAngle, int direction) {
        super(centerX, centerY, frames);
        this.startAngle = startAngle;
        this.maxAngle = maxAngle;
        this.direction = direction;
    }

    /**
     * @param calendar
     * @param dataRepository
     * @return float The progress between 0.0 and 1.0
     */
    abstract float getProgress(Calendar calendar, DataRepository dataRepository);

    @Override
    public void draw(int viewCenterX, int viewCenterY, Canvas canvas, Calendar calendar, DataRepository dataRepository) {
        Drawable drawable = getFrame();
        int centerX = viewCenterX + this.centerX;
        int centerY = viewCenterY + this.centerY;
        int halfWidth = drawable.getIntrinsicWidth() / 2;
        int halfHeight = drawable.getIntrinsicHeight() / 2;
        float angle = getProgress(calendar, dataRepository) * maxAngle;

        if (direction == OpenWatchFaceConstants.DIRECTION_CLOCKWISE) {
            angle = startAngle + angle;
        } else if (direction == OpenWatchFaceConstants.DIRECTION_COUNTERCLOCKWISE) {
            angle = startAngle - angle;
        }

        canvas.save();
        canvas.rotate(angle, (float) centerX, (float) centerY);
        drawable.setBounds(centerX - halfWidth, centerY - halfHeight, centerX + halfWidth, centerY + halfHeight);
        drawable.draw(canvas);
        canvas.restore();
    }
}
