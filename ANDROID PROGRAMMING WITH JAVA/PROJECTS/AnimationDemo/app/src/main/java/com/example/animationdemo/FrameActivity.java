package com.example.animationdemo;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FrameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frame);


        ImageView animateView = findViewById(R.id.imgView);

        animateView.setBackgroundResource(R.drawable.frame_animation);
        AnimationDrawable frameAnimation = (AnimationDrawable)animateView.getBackground();
        Log.d("Running", String.valueOf(frameAnimation.isRunning()));
        //start -> flipbook
        frameAnimation.start();
    }
}