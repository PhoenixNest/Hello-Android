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
import com.dev.a0228calculation.databinding.FragmentLoseBinding;
import com.dev.a0228calculation.viewmodel.MyViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoseFragment extends Fragment {

    public LoseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MyViewModel myViewModel = new ViewModelProvider(requireActivity(),
                new SavedStateViewModelFactory(requireActivity().getApplication(), requireActivity())).get(MyViewModel.class);
        FragmentLoseBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lose, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        binding.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_loseFragment_to_titleFragment);
            }
        });

        return binding.getRoot();
    }
}
