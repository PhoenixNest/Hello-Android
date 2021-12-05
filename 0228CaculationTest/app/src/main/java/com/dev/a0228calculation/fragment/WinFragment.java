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
import com.dev.a0228calculation.databinding.FragmentWinBinding;
import com.dev.a0228calculation.viewmodel.MyViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class WinFragment extends Fragment {

    public WinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MyViewModel myViewModel = new ViewModelProvider(requireActivity(),
                new SavedStateViewModelFactory(requireActivity().getApplication(), requireActivity())).get(MyViewModel.class);
        FragmentWinBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_win, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        binding.button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_winFragment_to_titleFragment);
            }
        });

        return binding.getRoot();
    }
}
