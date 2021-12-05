package com.dev.a0227savastatehandleviewmodelrestore;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private SavedStateHandle handle;

    private final static String KEY_NUMBER = "number";

    public MyViewModel(SavedStateHandle handle) {
        this.handle = handle;
    }

    public MutableLiveData<Integer> getNumber() {
        if (!handle.contains(KEY_NUMBER)) {
            handle.set(KEY_NUMBER, 0);

        }

        return handle.getLiveData(KEY_NUMBER);
    }

    public void add() {
        getNumber().setValue(getNumber().getValue() + 1);
    }
}
