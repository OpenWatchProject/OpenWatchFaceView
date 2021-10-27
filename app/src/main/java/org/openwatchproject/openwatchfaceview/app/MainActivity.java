package org.openwatchproject.openwatchfaceview.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.openwatchproject.openwatchfaceview.OpenWatchFaceView;

public class MainActivity extends AppCompatActivity {

    private OpenWatchFaceView watchFaceView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        watchFaceView = findViewById(R.id.watchface);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            // TODO: Open watchface picker
        });
    }
}