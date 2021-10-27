package org.openwatchproject.openwatchfaceview;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.TimeZone;

public class OpenWatchFaceView extends View {
    private static final String TAG = "OpenWatchWatchFaceView";

    private Calendar calendar;
    private DataRepository dataRepository = null;
    private OpenWatchFace watchFace = null;

    private int viewWidth;
    private int viewHeight;
    private int viewCenterX;
    private int viewCenterY;
    private float scaleX;
    private float scaleY;

    private boolean receivedRegistered = false;
    private boolean forceStop = false;
    private boolean shouldRunTicker = false;
    private int frameRate = 60;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_TIMEZONE_CHANGED.equals(intent.getAction())) {
                calendar.setTimeZone(TimeZone.getDefault());
            }
        }
    };

    public OpenWatchFaceView(Context context) {
        super(context);
        init();
    }

    public OpenWatchFaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpenWatchFaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public OpenWatchFaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        this.calendar = Calendar.getInstance();
    }

    public void setWatchFace(OpenWatchFace watchFace) {
        this.watchFace = watchFace;
        calculateScale();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (!receivedRegistered) {
            receivedRegistered = true;

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
            getContext().registerReceiver(receiver, intentFilter);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (receivedRegistered) {
            receivedRegistered = false;

            getContext().unregisterReceiver(receiver);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.viewWidth = w;
        this.viewHeight = h;
        this.viewCenterX = w / 2;
        this.viewCenterY = h / 2;

        calculateScale();
    }

    private void calculateScale() {
        if (watchFace != null) {
            float watchFaceAspectRatio = ((float) watchFace.getWidth()) / ((float) watchFace.getHeight());
            float displayAspectRatio = ((float) viewWidth) / ((float) viewHeight);
            if (displayAspectRatio > watchFaceAspectRatio) {
                scaleX = ((float) viewHeight) * watchFaceAspectRatio / ((float) watchFace.getWidth());
                scaleY = ((float) viewHeight) / ((float) watchFace.getHeight());
            } else {
                scaleX = ((float) viewWidth) / ((float) watchFace.getWidth());
                scaleY = ((float) viewWidth) / watchFaceAspectRatio / ((float) watchFace.getHeight());
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (watchFace != null) {
                return watchFace.onTapAction(getContext(), viewCenterX, viewCenterY, e.getX(), e.getY());
            }
            return false;
        }
    });

    public OpenWatchFace getWatchFace() {
        return watchFace;
    }

    private final Runnable ticker = new Runnable() {
        public void run() {
            if (shouldRunTicker) {
                invalidate();
                getHandler().postDelayed(ticker, 1000 / frameRate);
            }
        }
    };

    @Override
    public void onVisibilityAggregated(boolean isVisible) {
        super.onVisibilityAggregated(isVisible);

        if (!forceStop) {
            if (!shouldRunTicker && isVisible) {
                shouldRunTicker = true;
                ticker.run();
            } else if (shouldRunTicker && !isVisible) {
                shouldRunTicker = false;
                getHandler().removeCallbacks(ticker);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.scale(scaleX, scaleY, viewCenterX, viewCenterY);
        draw(viewCenterX, viewCenterY, canvas);
        canvas.restore();
    }

    public void draw(int viewCenterX, int viewCenterY, @NonNull Canvas canvas) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (watchFace != null && dataRepository != null) {
            watchFace.draw(viewCenterX, viewCenterY, canvas, calendar, dataRepository);
        }
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public void stopTicker() {
        if (!forceStop) {
            forceStop = true;
            if (shouldRunTicker) {
                shouldRunTicker = false;
                getHandler().removeCallbacks(ticker);
            }
        }
    }

    public void startTicker() {
        if (forceStop) {
            forceStop = false;
            if (!shouldRunTicker) {
                shouldRunTicker = true;
                ticker.run();
            }
        }
    }

    public void setDataRepository(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }
}
