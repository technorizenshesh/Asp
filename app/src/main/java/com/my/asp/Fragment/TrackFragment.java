package com.my.asp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.my.asp.R;
import com.my.asp.act.HomeActivity;
import com.my.asp.act.PartforGasActivity;
import com.my.asp.databinding.FragmentContactBinding;
import com.my.asp.databinding.FragmentTrakingBinding;
import com.my.asp.model.ProductDetailsModels;
import com.my.asp.model.TrackOrderModels;
import com.my.asp.utils.RetrofitClients;
import com.my.asp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TrackFragment extends Fragment {

    private SessionManager sessionManager;
    FragmentTrakingBinding binding;
    String OrderNumber="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_traking, container, false);

        sessionManager = new SessionManager(getActivity());

        OrderNumber= sessionManager.getOrderNumber();

        binding.RRContact.setOnClickListener(v -> {

            ((HomeActivity)getActivity()).setData("Contacts");
            Navigation.findNavController(v).navigate(R.id.action_tracking_to_navigation_Contacts);

        });



        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sessionManager.isNetworkAvailable()) {

            TrackOrderMethod();
        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }
    }

    private void TrackOrderMethod()
    {
        binding.progressBar.setVisibility(View.VISIBLE);
        Call<TrackOrderModels> call = RetrofitClients.getInstance().getApi()
                .Api_get_my_order(OrderNumber);
        call.enqueue(new Callback<TrackOrderModels>() {
            @Override
            public void onResponse(Call<TrackOrderModels> call, Response<TrackOrderModels> response) {
                binding.progressBar.setVisibility(View.GONE);
                TrackOrderModels finallyPr = response.body();
                if (finallyPr.getStatus().equalsIgnoreCase("1"))
                {
                    binding.txtEmty.setVisibility(View.GONE);
                    binding.llData.setVisibility(View.VISIBLE);
                    binding.txtPo.setText(finallyPr.getResult().getPo());
                    binding.txtOC.setText(finallyPr.getResult().getOc());
                    binding.txtReadiness.setText(finallyPr.getResult().getReadiness());
                    binding.txtExpedition.setText(finallyPr.getResult().getExpedition());
                    binding.txtOrderNumber.setText(finallyPr.getResult().getOrderNumber());
                    binding.txtArrival.setText(finallyPr.getResult().getInvoicing());
                    binding.txtOrderNotes.setText(finallyPr.getResult().getOrderNotes());


                }else {
                    binding.llData.setVisibility(View.GONE);
                    binding.txtEmty.setVisibility(View.VISIBLE);
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<TrackOrderModels> call, Throwable t)
            {
                binding.llData.setVisibility(View.GONE);
                binding.txtEmty.setVisibility(View.VISIBLE);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}