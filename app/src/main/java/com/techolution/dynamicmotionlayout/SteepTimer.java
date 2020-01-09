package com.techolution.dynamicmotionlayout;

import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class SteepTimer {

    private final ScheduledExecutorService mExecutors;
    private long mTimeInFuture;
    private final int mFutureTime;
    private int addTime = 0;
    private ScheduledFuture mSchedule;

    public SteepTimer(int futureTime) {
        mFutureTime = futureTime;
        mExecutors = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * Runnable to handle the count down.
     */
    private void run() {
//        long timeLeft = SystemClock.elapsedRealtime();
        if(addTime < mFutureTime){
            Log.i("timer0",""+addTime);
            addTime = addTime+1;
            Log.i("timer1",""+addTime+1);
            onTick(addTime);
        }else {
            reset();
        }

//        if (timeLeft <= 0) {
////            reset();
//        } else {
//            onTick(timeLeft);
//        }
    }

    /**
     * Start the timer count down until the future time.
     */
    public void start() {
//        mTimeInFuture = SystemClock.elapsedRealtime() + mFutureTime;
        if (mSchedule == null) {
            mSchedule = mExecutors.scheduleAtFixedRate(this::run, 0, 100, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Rest the schedule count down time.
     */
    public void reset() {
        if (mSchedule != null) {
            mSchedule.cancel(false);
            mSchedule = null;
            onTick(mFutureTime);
        }
    }

    /**
     * Called on every 1000 milliseconds with milliseconds left until the future time.
     *
     * @param millisUntilFinished count down to future time in milliseconds.
     */
    public abstract void onTick(int millisUntilFinished);

}