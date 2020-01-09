package com.techolution.dynamicmotionlayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimerViewModel extends ViewModel {

    private final MutableLiveData<Integer> mLapseTime = new MutableLiveData<>();
    private SteepTimer mTimer;

    public TimerViewModel() {
    }

    /**
     * Set how long the steep timer should run.
     *
     * @param millisTime timer time in milliseconds.
     */
    public void setSteepTimer(int millisTime) {
        if (mTimer == null) {
            mLapseTime.postValue(millisTime);
            mTimer = new SteepTimer(millisTime) {
                @Override
                public void onTick(int millisUntilFinished) {
                    mLapseTime.postValue(millisUntilFinished);
                }
            };
        }
    }

    /**
     * Trigger timer to start.
     */
    public void start() {
        if (mTimer != null) {
            mTimer.start();
        }
    }

    /**
     * Reset timer to the beginning.
     */
    public void reset() {
        if (mTimer != null) {
            mTimer.reset();
        }
    }

    public LiveData<Integer> getTimer() {
        return mLapseTime;
    }

}
