package com.dev.a0301bottomnavigationbar.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dev.a0301bottomnavigationbar.R;
import com.dev.a0301bottomnavigationbar.viewmodel.ThirdViewModel;

import java.util.Random;

public class ThirdFragment extends Fragment {

    private ThirdViewModel mViewModel;
    private ImageView imageView;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment, container, false);
        imageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(ThirdViewModel.class);
        // TODO: Use the ViewModel
        imageView.setX(mViewModel.dx);
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "x", 0, 0);
        objectAnimator.setDuration(500);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!objectAnimator.isRunning()) {
                    float dx = new Random().nextBoolean() ? 100 : -100;
                    objectAnimator.setFloatValues(imageView.getX(), imageView.getX() + dx);
                    mViewModel.dx += dx;
                    objectAnimator.start();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG_Third", "onDestroy: ");
    }
}
