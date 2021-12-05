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
import com.dev.a0228calculation.databinding.FragmentQuestionBinding;
import com.dev.a0228calculation.viewmodel.MyViewModel;

public class QuestionFragment extends Fragment {
    private MyViewModel myViewModel;
    private FragmentQuestionBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myViewModel = new ViewModelProvider(requireActivity(),
                new SavedStateViewModelFactory(requireActivity().getApplication(), requireActivity())).get(MyViewModel.class);
        myViewModel.getCorrectScore().setValue(0);
        myViewModel.generator();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        final StringBuilder stringBuilder = new StringBuilder();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button0:
                        stringBuilder.append("0");
                        break;

                    case R.id.button1:
                        stringBuilder.append("1");
                        break;

                    case R.id.button2:
                        stringBuilder.append("2");
                        break;

                    case R.id.button3:
                        stringBuilder.append("3");
                        break;

                    case R.id.button4:
                        stringBuilder.append("4");
                        break;

                    case R.id.button5:
                        stringBuilder.append("5");
                        break;

                    case R.id.button6:
                        stringBuilder.append("6");
                        break;

                    case R.id.button7:
                        stringBuilder.append("7");
                        break;

                    case R.id.button8:
                        stringBuilder.append("8");
                        break;

                    case R.id.button9:
                        stringBuilder.append("9");
                        break;

                    case R.id.button_clear:
                        stringBuilder.setLength(0);
                        break;
                }

                if (stringBuilder.length() == 0) {
                    binding.textView9.setText(getString(R.string.input_ready));
                } else {
                    binding.textView9.setText(stringBuilder.toString());
                }
            }
        };

        binding.button0.setOnClickListener(clickListener);
        binding.button1.setOnClickListener(clickListener);
        binding.button2.setOnClickListener(clickListener);
        binding.button3.setOnClickListener(clickListener);
        binding.button4.setOnClickListener(clickListener);
        binding.button5.setOnClickListener(clickListener);
        binding.button6.setOnClickListener(clickListener);
        binding.button7.setOnClickListener(clickListener);
        binding.button8.setOnClickListener(clickListener);
        binding.button9.setOnClickListener(clickListener);
        binding.buttonClear.setOnClickListener(clickListener);

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onClick(View v) {
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("-1");
                }
                if (Integer.valueOf(stringBuilder.toString()).intValue() == myViewModel.getAnswer().getValue()) {
                    myViewModel.answerCorrect();
                    stringBuilder.setLength(0);
                    binding.textView9.setText(getString(R.string.answer_correct_msg));
                } else {
                    NavController navController = Navigation.findNavController(v);
                    if (myViewModel.winFlag) {
                        navController.navigate(R.id.action_questionFragment_to_winFragment);
                        myViewModel.winFlag = false;
                        myViewModel.save();
                    } else {
                        navController.navigate(R.id.action_questionFragment_to_loseFragment);
                    }
                }
            }
        });

        return binding.getRoot();
    }
}
