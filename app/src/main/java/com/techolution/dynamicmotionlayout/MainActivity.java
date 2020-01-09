package com.techolution.dynamicmotionlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class MainActivity extends AppCompatActivity {
    MotionLayout motionLayout, ml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        motionLayout = findViewById(R.id.parent_l);

        addNewView();

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewView();
            }
        });

        TimerViewModel viewModel = new TimerViewModel();
        viewModel.setSteepTimer(500);
        viewModel.start();
        viewModel.getTimer().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer aLong) {
                Log.i("timer",""+aLong);
            }
        });

//        for (int i = 0; i<= 4; i++){
////            try {
////                Thread.sleep(1500);
//                addNewView();
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//        }


    }

    private void addNewView() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.face_left_anim, null);

        motionLayout.addView(view, 0,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        ml = motionLayout.findViewById(R.id.motion_layout9);
        ml.transitionToStart();
        ml.transitionToEnd();

        RingProgressBar mProgress = ml.findViewById(R.id.face_left).findViewById(R.id.progress_bar_3);
        ImageView imageView = ml.findViewById(R.id.face_left).findViewById(R.id.circular_image);
        imageView.setImageResource(R.drawable.ic_launcher_background);
        TimerViewModel viewModel = new TimerViewModel();
        viewModel.setSteepTimer(100);
        viewModel.start();
        viewModel.getTimer().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer aLong) {
                Log.i("timer",""+aLong);
                mProgress.setProgress(aLong);
            }
        });
        addListner();
    }

    private void addListner() {
        ml.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {
                Log.i("motion start:",""+i+" : "+i1);
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {
//                Log.i("motion change :",""+i+" : "+i1);
//                if(v>50 && v <80){
//                    Log.i("motion change :",""+i+" : "+i1);
//                    addNewView();
//                }

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                motionLayout.removeAllViews();
//                addNewView();
                Log.i("motion complete:",""+i);
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {
                Log.i("motion ontrigger:",""+i+" : "+b+" : "+v);
            }

            @Override
            public boolean allowsTransition(MotionScene.Transition transition) {
                return false;
            }
        });
    }
}
