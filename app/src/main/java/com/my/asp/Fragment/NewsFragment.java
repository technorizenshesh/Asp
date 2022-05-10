package com.my.asp.Fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.my.asp.adapter.NewsAdapter;
import com.my.asp.adapter.ProductAdapter;
import com.my.asp.databinding.FragmentDashboardBinding;
import com.my.asp.databinding.FragmentNewsBinding;
import com.my.asp.model.NewsModels;
import com.my.asp.model.ProductModel;
import com.my.asp.model.ProductModels;
import com.my.asp.utils.RetrofitClients;
import com.my.asp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFragment extends Fragment {


    FragmentNewsBinding  binding;
    NewsAdapter adapter;
    private SessionManager sessionManager;
    private ArrayList<NewsModels.Result> modelList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_news, container, false);

        sessionManager = new SessionManager(getActivity());

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sessionManager.isNetworkAvailable()) {
            binding.progressBar.setVisibility(View.VISIBLE);
            GetNewsMethod();
        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapter(ArrayList<NewsModels.Result> modelList) {
/*

        this.modelList.add(new ProductModel("Parts for Diesel Engines",R.drawable.img_pr));
        this.modelList.add(new ProductModel("Parts for Diesel Engines",R.drawable.pr_item));
        this.modelList.add(new ProductModel("Auxiliary Parts",R.drawable.pr_item));
        this.modelList.add(new ProductModel("Maintenance Services",R.drawable.pr_item_one));
        this.modelList.add(new ProductModel("Installation of Power Plants (EPC)",R.drawable.pr_item_two));
*/

        adapter = new NewsAdapter(getActivity(), this.modelList);
        binding.recyclerproduct.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerproduct.setLayoutManager(linearLayoutManager);
        binding.recyclerproduct.setAdapter(adapter);
        adapter.SetOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, NewsModels.Result model) {

            }
        });
    }

    private void GetNewsMethod()
    {
        Call<NewsModels> call = RetrofitClients.getInstance().getApi()
                .get_news();
        call.enqueue(new Callback<NewsModels>() {
            @Override
            public void onResponse(Call<NewsModels> call, Response<NewsModels> response) {
                binding.progressBar.setVisibility(View.GONE);
                NewsModels finallyPr = response.body();
                if (finallyPr.getStatus().equalsIgnoreCase("1"))
                {
                    modelList = (ArrayList<NewsModels.Result>) finallyPr.getResult();

                    setAdapter(modelList);

                }else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), finallyPr.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<NewsModels> call, Throwable t)
            {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}