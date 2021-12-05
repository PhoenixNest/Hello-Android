package com.dev.a0228viewmodelsp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {

    private SavedStateHandle handle;
    private String KEY = getApplication().getResources().getString(R.string.key);
    private String SP_NAME = getApplication().getResources().getString(R.string.spname);

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);

        this.handle = handle;
        if (!handle.contains(KEY)) {
            load();
        }
    }

    private void load() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        int value = sharedPreferences.getInt(KEY, 0);
        handle.set(KEY, value);
    }

    public LiveData<Integer> getNumber() {
        return handle.getLiveData(KEY);
    }

    void save() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(KEY, getNumber().getValue());
        edit.apply();
    }

    public void add(int value) {
        handle.set(KEY, getNumber().getValue() + value);
    }
}
