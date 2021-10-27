package org.openwatchproject.openwatchfaceview.item;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.openwatchproject.openwatchfaceview.DataRepository;

import java.util.Calendar;
import java.util.List;

public abstract class DrawableItem extends Item {
    private static final String TAG = "DrawableItem";

    /**
     * An array of frames that need to be displayed.
     * Frame count must be at least 1. If it's greater than 1, it's an animation.
     */
    protected final List<Drawable> frames;

    public DrawableItem(int centerX, int centerY, List<Drawable> frames) {
        super(centerX, centerY);
        this.frames = frames;
    }

    public void addFrame(Drawable frame) {
        this.frames.add(frame);
    }

    public void addAllFrames(List<Drawable> frames) {
        this.frames.addAll(frames);
    }

    public Drawable getFrame() {
        if (frames.size() == 1) {
            return frames.get(0);
        }
        // TODO: Implement animations
        Log.d(TAG, "getFrame: Missing animation functionality!");
        return frames.get(0);
    }

    public List<Drawable> getFrames() {
        return frames;
    }

    public abstract void draw(int viewCenterX, int viewCenterY, Canvas canvas, Calendar calendar, DataRepository dataRepository);

    public final void draw(int viewCenterX, int viewCenterY, Canvas canvas, Drawable... drawables) {
        int width = 0;
        for (Drawable drawable : drawables) {
            width += drawable.getIntrinsicWidth();
        }

        int centerX = viewCenterX + this.centerX;
        int centerY = viewCenterY + this.centerY;
        int left = centerX - (width / 2);
        for (Drawable drawable : drawables) {
            int halfHeight = drawable.getIntrinsicHeight() / 2;
            int top = centerY - halfHeight;
            int bottom = centerY + halfHeight;
            int right = left + drawable.getIntrinsicWidth();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
            left = right;
        }
    }
}
