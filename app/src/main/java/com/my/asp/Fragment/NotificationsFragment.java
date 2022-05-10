package com.my.asp.Fragment;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.my.asp.R;
import com.my.asp.adapter.NotificationAdapter;
import com.my.asp.databinding.FragmentNotificationsBinding;
import com.my.asp.model.NotificationList;
import com.my.asp.model.ProductModels;
import com.my.asp.utils.RetrofitClients;
import com.my.asp.utils.SessionManager;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationsFragment extends Fragment {

    NotificationAdapter adapter;
    private ArrayList<NotificationList.Result> modelList = new ArrayList<>();
    FragmentNotificationsBinding binding;
    private SessionManager sessionManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_notifications, container, false);

        sessionManager = new SessionManager(getActivity());

        if (sessionManager.isNetworkAvailable()) {
            binding.progressBar.setVisibility(View.VISIBLE);
            GetNotificationMethod();
        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }

        return binding.getRoot();
    }

    private void setAdapter(ArrayList<NotificationList.Result> modelList) {

        adapter = new NotificationAdapter(getActivity(), this.modelList);
        binding.recyclerNotification.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerNotification.setLayoutManager(linearLayoutManager);
        binding.recyclerNotification.setAdapter(adapter);
        adapter.SetOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, NotificationList.Result model) {


            }
        });
    }

    public void GetNotificationMethod()
    {
        binding.progressBar.setVisibility(View.VISIBLE);
        Call<NotificationList> call = RetrofitClients.getInstance().getApi()
                .Api_get_notification();
        call.enqueue(new Callback<NotificationList>() {
            @Override
            public void onResponse(Call<NotificationList> call, Response<NotificationList> response) {
                binding.progressBar.setVisibility(View.GONE);
                NotificationList finallyPr = response.body();
                if (finallyPr.getStatus().equalsIgnoreCase("1"))
                {

                    binding.txtEmty.setVisibility(View.GONE);
                    modelList = (ArrayList<NotificationList.Result>) finallyPr.getResult();

                    setAdapter(modelList);

                }else {
                    binding.txtEmty.setVisibility(View.VISIBLE);
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<NotificationList> call, Throwable t)
            {
                binding.txtEmty.setVisibility(View.VISIBLE);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }


}