package com.my.asp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.my.asp.R;
import com.my.asp.databinding.FragmentAboutsBinding;
import com.my.asp.databinding.FragmentContactBinding;


public class AboutsFragment extends Fragment {

    FragmentAboutsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_abouts, container, false);


        return binding.getRoot();
    }
}