package com.dev.a0229room;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dev.a0229room.adapter.RVAdapter;
import com.dev.a0229room.databinding.ActivityMainBinding;
import com.dev.a0229room.db.Word;
import com.dev.a0229room.viewmodel.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private WordViewModel wordViewModel;

    private RVAdapter rvAdapter;
    private RVAdapter rvCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        rvAdapter = new RVAdapter(false, wordViewModel);
        rvCardAdapter = new RVAdapter(true, wordViewModel);

        binding.rvMain.setLayoutManager(new LinearLayoutManager(this));
        binding.rvMain.setAdapter(rvAdapter);
//        binding.rvMain.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.rvMain.setAdapter(rvCardAdapter);
                } else {
                    binding.rvMain.setAdapter(rvAdapter);
                }
            }
        });

        wordViewModel.getLiveWordsList().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                int temp = rvAdapter.getItemCount();

                rvAdapter.setWordList(words);
                rvCardAdapter.setWordList(words);

                if (temp != words.size()) {
                    rvAdapter.notifyDataSetChanged();
                    rvCardAdapter.notifyDataSetChanged();
                }
            }
        });

        binding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("Hello", "你好");
                Word word2 = new Word("World", "世界");
                Word word3 = new Word("Android", "安卓");
                Word word4 = new Word("Apple", "苹果");

                wordViewModel.insertWords(word, word2, word3, word4);
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("Hi", "嗨");
                word.setId(20);
                wordViewModel.updateWords(word);
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("Hi", "嗨");
                word.setId(80);
                wordViewModel.deleteWords(word);

            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordViewModel.deleteALlWords();
            }
        });
    }

}
