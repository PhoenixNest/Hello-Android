package com.dev.a0228advancednavigation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText edt = getView().findViewById(R.id.editText);
        getView().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt.getText())) {
                    Toast.makeText(getActivity(), "Please Input Something", Toast.LENGTH_SHORT).show();
                }

                Bundle bundle = new Bundle();
                bundle.putString("MYNAME", edt.getText().toString());

                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_detailsFragment, bundle);
            }
        });
    }
}
