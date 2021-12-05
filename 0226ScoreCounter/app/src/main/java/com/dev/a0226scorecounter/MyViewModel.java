package com.dev.a0226scorecounter;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private SavedStateHandle handle;

    private MutableLiveData<Integer> aTeamScore;
    private MutableLiveData<Integer> bTeamScore;

    private int aBack, bBack;

    public MyViewModel(SavedStateHandle handle) {
        if (!handle.contains("NUMBER")) {
            handle.set("NUMBER", 0);
        }
        this.handle = handle;
    }

    public MutableLiveData<Integer> getaTeamScore() {
        if (aTeamScore == null) {
            aTeamScore = new MediatorLiveData<>();
            aTeamScore.setValue(0);
        }
        return aTeamScore;
    }

    public MutableLiveData<Integer> getbTeamScore() {
        if (bTeamScore == null) {
            bTeamScore = new MediatorLiveData<>();
            bTeamScore.setValue(0);
        }

        return bTeamScore;
    }

    public void aTeamAdd(int value) {
        aBack = aTeamScore.getValue();
        bBack = bTeamScore.getValue();
        aTeamScore.setValue(aTeamScore.getValue() + value);
    }

    public void bTeamAdd(int value) {
        aBack = aTeamScore.getValue();
        bBack = bTeamScore.getValue();
        bTeamScore.setValue(bTeamScore.getValue() + value);
    }

    public void undo() {
        aTeamScore.setValue(aBack);
        bTeamScore.setValue(bBack);
    }

    public void reset() {
        aBack = aTeamScore.getValue();
        bBack = bTeamScore.getValue();
        aTeamScore.setValue(0);
        bTeamScore.setValue(0);
    }
}
