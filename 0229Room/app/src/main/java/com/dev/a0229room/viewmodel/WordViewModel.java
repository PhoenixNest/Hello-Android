package com.dev.a0229room.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dev.a0229room.db.Word;
import com.dev.a0229room.repository.WordRepository;

import java.util.List;

//    管理UI数据
public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<List<Word>> getLiveWordsList() {
        return wordRepository.getLiveWordList();
    }

    public void insertWords(Word... words) {
        wordRepository.insertWords(words);
    }

    public void updateWords(Word... words) {
        wordRepository.updateWords(words);
    }

    public void deleteWords(Word... words) {
        wordRepository.deleteWords(words);
    }

    public void deleteALlWords( ) {
        wordRepository.deleteALlWords();
    }
}
