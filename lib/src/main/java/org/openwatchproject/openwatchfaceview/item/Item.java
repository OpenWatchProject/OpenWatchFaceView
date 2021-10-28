package org.openwatchproject.openwatchfaceview.item;

public abstract class Item {
    /**
     * Indicates the horizontal center for the item in pixels.
     */
    final int centerX;

    /**
     * Indicates the vertical center for the item in pixels.
     */
    final int centerY;

    public Item(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }
}
