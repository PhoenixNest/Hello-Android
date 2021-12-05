package com.dev.a0228calculation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;

public class MyViewModel extends AndroidViewModel {
    private final static String KEY_HIGH_SCORE = "HIGH_SCORE";
    private final static String KEY_LEFT_NUMBER = "KEY_LEFT_NUMBER";
    private final static String KEY_RIGHT_NUMBER = "KEY_RIGHT_NUMBER";
    private final static String KEY_OPERATOR = "KEY_OPERATOR";
    private final static String KEY_CURRENT_SCORE = "KEY_CURRENT_SCORE";
    private final static String KEY_ANSWER = "KEY_ANSWER";

    private final static String SP_SAVE_TIME = "SA_SAVE_TIME";

    private SavedStateHandle handle;
    public boolean winFlag = false;

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        if (!handle.contains(KEY_HIGH_SCORE)) {
            SharedPreferences sharedPreferences = getApplication().getSharedPreferences(SP_SAVE_TIME, Context.MODE_PRIVATE);
            handle.set(KEY_HIGH_SCORE, sharedPreferences.getInt(KEY_HIGH_SCORE, 0));
            handle.set(KEY_LEFT_NUMBER, 0);
            handle.set(KEY_RIGHT_NUMBER, 0);
            handle.set(KEY_OPERATOR, "*");
            handle.set(KEY_CURRENT_SCORE, 0);
            handle.set(KEY_ANSWER, 0);
        }

        this.handle = handle;
    }

    public MutableLiveData<Integer> getHighScore() {
        return handle.getLiveData(KEY_HIGH_SCORE);
    }

    public MutableLiveData<Integer> getLeftNumber() {
        return handle.getLiveData(KEY_LEFT_NUMBER);
    }

    public MutableLiveData<Integer> getRightNumber() {
        return handle.getLiveData(KEY_RIGHT_NUMBER);
    }

    public MutableLiveData<String> getOperator() {
        return handle.getLiveData(KEY_OPERATOR);
    }

    public MutableLiveData<Integer> getCorrectScore() {
        return handle.getLiveData(KEY_CURRENT_SCORE);
    }

    public MutableLiveData<Integer> getAnswer() {
        return handle.getLiveData(KEY_ANSWER);
    }

    public void generator() {
        int LEVEL = 20;
        Random random = new Random();
        int x, y;
        x = random.nextInt(LEVEL) + 1;
        y = random.nextInt(LEVEL) + 1;

        if (x % 2 == 0) {
            getOperator().setValue("+");
            if (x > y) {
                // 如Answer为10，此时设x为10，y即成为第一加法元，第二加法元为10 - y
                getAnswer().setValue(x);
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x - y);
            } else {
                getAnswer().setValue(y);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y - x);
            }

        } else {
            getOperator().setValue("-");
            if (x > y) {
                getAnswer().setValue(x - y);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y);
            } else {
                getAnswer().setValue(y - x);
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    public void save() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(SP_SAVE_TIME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_HIGH_SCORE, getHighScore().getValue());
        editor.apply();
    }

    @SuppressWarnings("ConstantConditions")
    public void answerCorrect() {
        getCorrectScore().setValue(getCorrectScore().getValue() + 1);
        if (getCorrectScore().getValue() > getHighScore().getValue()) {
            getHighScore().setValue(getCorrectScore().getValue());
            winFlag = true;
        }
        generator();
    }
}
