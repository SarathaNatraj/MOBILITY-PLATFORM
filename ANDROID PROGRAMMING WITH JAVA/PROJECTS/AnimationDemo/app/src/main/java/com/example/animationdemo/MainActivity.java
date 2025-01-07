package com.example.animationdemo;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View myView = findViewById(R.id.my_view);
        View redView = findViewById(R.id.my_view_ani);
        Button custom = findViewById(R.id.custom);
        Button animate = findViewById(R.id.animate);
        Button frame= findViewById(R.id.frame);

        myView.setVisibility(View.VISIBLE);

       // Fragment f

        // Alpha animation: Fades the view in and out
        ObjectAnimator fadeAnimator = ObjectAnimator.ofFloat(myView, "alpha", 0f, 1f);
        fadeAnimator.setDuration(1000); // 1 second

        // Translation animation: Moves the view horizontally
        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(myView, "translationX", 0f, 300f);
        translateAnimator.setDuration(1000);

        // Rotation animation: Rotates the view 360 degrees
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(myView, "rotation", 0f, 360f);
        rotateAnimator.setDuration(1000);

        // Combine animations using AnimatorSet

        //
        Animator animator= AnimatorInflater.loadAnimator(this, R.animator.translation);
        animator.setTarget(redView); //attaching the view with animator(translation)


        animate.setOnClickListener(v -> {
            myView.post(() -> {
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(fadeAnimator, translateAnimator, rotateAnimator);
                animator.start();
                animatorSet.start();
            });


        });

        custom.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CustomAnimationActivity.class));
             });

        frame.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FrameActivity.class));
        });

        }
}
