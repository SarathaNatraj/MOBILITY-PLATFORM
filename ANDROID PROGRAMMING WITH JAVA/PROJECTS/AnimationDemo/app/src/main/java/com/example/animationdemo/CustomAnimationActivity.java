package com.example.animationdemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class CustomAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        final View myView = findViewById(R.id.my_view_cu);

        // Convert dp to pixels
        int startWidth = dpToPx(100);
        int endWidth = dpToPx(300);

        // ValueAnimator to change the width of a view
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startWidth, endWidth);
        valueAnimator.setDuration(1000); // 1 second

        valueAnimator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue(); //100, 120, 130,.... 300
            myView.getLayoutParams().width = animatedValue;
            myView.requestLayout();
        });

        valueAnimator.start();
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
