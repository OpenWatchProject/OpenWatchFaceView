package org.openwatchproject.openwatchfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;

import androidx.annotation.NonNull;

import org.openwatchproject.openwatchfaceview.item.AbstractItem;
import org.openwatchproject.openwatchfaceview.item.TapActionItem;

import java.util.ArrayList;
import java.util.Calendar;

public class OpenWatchFace {
    /**
     * The OpenWatch WatchFace format version
     */
    private static final int VERSION = 1;

    /**
     * The width in pixels for which this WatchFace was designed for.
     */
    private final int width;

    /**
     * The height in pixels for which this WatchFace was designed for.
     */
    private final int height;

    /**
     * The items that form this WatchFace.
     */
    private final ArrayList<AbstractItem> items;

    /**
     * The items that can start an action
     */
    private final ArrayList<TapActionItem> tapActionItems;

    private Uri path;

    public OpenWatchFace(int width, int height) {
        this.width = width;
        this.height = height;
        this.items = new ArrayList<>();
        this.tapActionItems = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPath(Uri path) {
        this.path = path;
    }

    public Uri getPath() {
        return path;
    }

    public void addItem(AbstractItem item) {
        items.add(item);
    }

    public void addTapActionItem(TapActionItem item) {
        tapActionItems.add(item);
    }

    public boolean onTapAction(Context context, int viewCenterX, int viewCenterY, float touchX, float touchY) {
        for (TapActionItem item : tapActionItems) {
            if (item.onTapAction(context, viewCenterX, viewCenterY, touchX, touchY)) {
                return true;
            }
        }
        return false;
    }

    public void draw(int viewCenterX, int viewCenterY, @NonNull Canvas canvas, @NonNull Calendar calendar, @NonNull DataRepository dataRepository) {
        for (AbstractItem i : items) {
            if (i.isDrawable()) {
                i.draw(viewCenterX, viewCenterY, canvas, calendar, dataRepository);
            }
        }
    }
}
