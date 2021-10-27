package org.openwatchproject.openwatchfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;

import androidx.annotation.NonNull;

import org.openwatchproject.openwatchfaceview.item.DrawableItem;
import org.openwatchproject.openwatchfaceview.item.Item;
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
    private final ArrayList<Item> items;

    private Uri path;

    private OpenWatchFace(int width, int height, ArrayList<Item> items, Uri path) {
        this.width = width;
        this.height = height;
        this.items = items;
        this.path = path;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Uri getPath() {
        return path;
    }

    public boolean onTapAction(Context context, int viewCenterX, int viewCenterY, float touchX, float touchY) {
        for (Item i : items) {
            if (i instanceof TapActionItem) {
                if (((TapActionItem) i).onTapAction(context, viewCenterX, viewCenterY, touchX, touchY)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(int viewCenterX, int viewCenterY, @NonNull Canvas canvas, @NonNull Calendar calendar, @NonNull DataRepository dataRepository) {
        for (Item i : items) {
            if (i instanceof DrawableItem) {
                ((DrawableItem) i).draw(viewCenterX, viewCenterY, canvas, calendar, dataRepository);
            }
        }
    }

    public static class Builder {
        private final int width;
        private final int height;
        private final ArrayList<Item> items;
        private Uri path;

        public Builder(int width, int height) {
            this.width = width;
            this.height = height;
            this.items = new ArrayList<>();
        }

        public Builder addItem(Item item) {
            this.items.add(item);
            return this;
        }

        public Builder setPath(Uri path) {
            this.path = path;
            return this;
        }

        public OpenWatchFace build() {
            return new OpenWatchFace(width, height, items, path);
        }
    }
}
