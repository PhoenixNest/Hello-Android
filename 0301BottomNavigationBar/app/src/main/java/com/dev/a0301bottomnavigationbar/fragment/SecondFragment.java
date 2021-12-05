package com.dev.a0301bottomnavigationbar.fragment;

import android.animation.AnimatorSet;
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
import com.dev.a0301bottomnavigationbar.viewmodel.SecondViewModel;

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;
    private ImageView imageView;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        imageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(SecondViewModel.class);
        // TODO: Use the ViewModel
        imageView.setScaleX(mViewModel.scaleValue);
        imageView.setScaleY(mViewModel.scaleValue);
        final ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 0, 0);
        final ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 0, 0);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorX, objectAnimatorY);
        animatorSet.setDuration(500);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!animatorSet.isRunning()) {
                    objectAnimatorX.setFloatValues(imageView.getScaleX() + 0.1f);
                    objectAnimatorY.setFloatValues(imageView.getScaleY() + 0.1f);
                    mViewModel.scaleValue += 0.1;
                    animatorSet.start();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG_Second", "onDestroy: ");
    }
}
