package com.my.asp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.my.asp.R;
import com.my.asp.act.PartforGasActivity;
import com.my.asp.adapter.ProductAdapter;
import com.my.asp.databinding.FragmentDashboardBinding;
import com.my.asp.model.ProductModels;
import com.my.asp.utils.RetrofitClients;
import com.my.asp.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductFragment extends Fragment {

    FragmentDashboardBinding binding;
    ProductAdapter adapter;
    private ArrayList<ProductModels.Result> modelList = new ArrayList<>();
    private SessionManager sessionManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_dashboard, container, false);

        sessionManager = new SessionManager(getActivity());

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // filter your list from your input
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sessionManager.isNetworkAvailable()) {
           // binding.progressBar.setVisibility(View.VISIBLE);
            GetProductMethod();
        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapter(ArrayList<ProductModels.Result> modelList) {

        adapter = new ProductAdapter(getActivity(), this.modelList);
        binding.recyclerproduct.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerproduct.setLayoutManager(linearLayoutManager);
        binding.recyclerproduct.setAdapter(adapter);
        adapter.SetOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ProductModels.Result model) {
                sessionManager.saveProductId(model.getId());
                startActivity(new Intent(getActivity(), PartforGasActivity.class));
            }
        });
    }

    void filter(String text){
        List<ProductModels.Result> temp = new ArrayList();
        for(ProductModels.Result d: modelList){
            if(d.getProductName().toLowerCase().contains(text.toLowerCase())){
                temp.add(d);
            }
        }
        adapter.updateList((ArrayList<ProductModels.Result>) temp);
    }


    public void GetProductMethod()
    {
        binding.progressBar.setVisibility(View.VISIBLE);
        Call<ProductModels> call = RetrofitClients.getInstance().getApi()
                .get_products();
        call.enqueue(new Callback<ProductModels>() {
            @Override
            public void onResponse(Call<ProductModels> call, Response<ProductModels> response) {
                binding.progressBar.setVisibility(View.GONE);
                ProductModels finallyPr = response.body();
                if (finallyPr.getStatus().equalsIgnoreCase("1"))
                {
                    modelList = (ArrayList<ProductModels.Result>) finallyPr.getResult();
                    setAdapter(modelList);
                }else {
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<ProductModels> call, Throwable t)
            {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}