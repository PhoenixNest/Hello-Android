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
import com.dev.a0301bottomnavigationbar.viewmodel.FirstViewModel;

public class FirstFragment extends Fragment {

    private FirstViewModel mViewModel;
    private ImageView imageView;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        imageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(FirstViewModel.class);
        // TODO: Use the ViewModel
        imageView.setRotation(mViewModel.rotatePosition);
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 0);
        objectAnimator.setDuration(500);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!objectAnimator.isRunning()) {
                    objectAnimator.setFloatValues(imageView.getRotation(), imageView.getRotation() + 100);
                    mViewModel.rotatePosition += 100;
                    objectAnimator.start();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG_First", "onDestroy: ");
    }
}
