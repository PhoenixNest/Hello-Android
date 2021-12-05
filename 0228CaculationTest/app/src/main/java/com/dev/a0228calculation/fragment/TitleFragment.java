package com.dev.a0228calculation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.dev.a0228calculation.R;
import com.dev.a0228calculation.databinding.FragmentTitleBinding;
import com.dev.a0228calculation.viewmodel.MyViewModel;

public class TitleFragment extends Fragment {

    private MyViewModel myViewModel;
    private FragmentTitleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myViewModel = new ViewModelProvider(requireActivity(),
                new SavedStateViewModelFactory(requireActivity().getApplication(), requireActivity())).get(MyViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_titleFragment_to_questionFragment);
            }
        });

        return binding.getRoot();
    }
}
