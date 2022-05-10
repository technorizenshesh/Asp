package com.my.asp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.my.asp.R;
import com.my.asp.act.HomeActivity;
import com.my.asp.act.ProductAddWeb;
import com.my.asp.databinding.FragmentHomeBinding;
import com.my.asp.utils.SessionManager;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    private SessionManager sessionManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);

        sessionManager = new SessionManager(getActivity());

        binding.RRTrack.setOnClickListener(v -> {

            String orderNumber= binding.edtOrdernumber.getText().toString();

            if(!orderNumber.equalsIgnoreCase(""))
            {
                sessionManager.saveOrderNumber(orderNumber);

                ((HomeActivity)getActivity()).setData("Tracking");
                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_tracking);

            }else
            {
                Toast.makeText(getActivity(), "Please Enter Order Number", Toast.LENGTH_SHORT).show();
            }

        });

        binding.RRAbouts.setOnClickListener(v -> {

           ((HomeActivity)getActivity()).setData("Product");
             Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_navigation_Product);
           // startActivity(new Intent(getActivity(), ProductAddWeb.class).putExtra("Url","https://www.atlantic.com.pt/contacto/"));

        });

        return binding.getRoot();
    }
}