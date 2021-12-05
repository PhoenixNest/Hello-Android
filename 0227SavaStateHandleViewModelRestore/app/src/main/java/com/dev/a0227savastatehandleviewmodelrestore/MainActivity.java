package com.dev.a0227savastatehandleviewmodelrestore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;

import com.dev.a0227savastatehandleviewmodelrestore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        myViewModel = ViewModelProviders.of(this,
                new SavedStateViewModelFactory(getApplication(), this)).get(MyViewModel.class);

        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);
    }
}
