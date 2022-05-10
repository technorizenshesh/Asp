package com.my.asp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.my.asp.R;
import com.my.asp.adapter.ProductAdapter;
import com.my.asp.adapter.ProductAdapterDetails;
import com.my.asp.databinding.ActivityPartforGasBinding;
import com.my.asp.model.ProductDetailsModels;
import com.my.asp.model.ProductModel;
import com.my.asp.model.ProductModels;
import com.my.asp.utils.RetrofitClients;
import com.my.asp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartforGasActivity extends AppCompatActivity {

    ActivityPartforGasBinding binding;
    ProductAdapterDetails adapter;
    private ArrayList<ProductDetailsModels.Result.PartsDetail> modelList = new ArrayList<>();
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_partfor_gas);

        sessionManager = new SessionManager(PartforGasActivity.this);

        binding.RRback.setOnClickListener(v -> {
            onBackPressed();
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if (sessionManager.isNetworkAvailable()) {
            binding.progressBar.setVisibility(View.VISIBLE);
            GetProductDetailsMethod();
        }else {
            Toast.makeText(PartforGasActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapter(ArrayList<ProductDetailsModels.Result.PartsDetail> modelList) {

    /*    this.modelList.add(new ProductModel("Cylinder Head",R.drawable.img_pr));
        this.modelList.add(new ProductModel("Piston",R.drawable.pr_item));
        this.modelList.add(new ProductModel("Cylinder Liner",R.drawable.pr_item));
        this.modelList.add(new ProductModel("Filters",R.drawable.pr_item_one));
        this.modelList.add(new ProductModel("Spark Plug",R.drawable.pr_item_two));
        this.modelList.add(new ProductModel("Valves",R.drawable.pr_item_two));
        this.modelList.add(new ProductModel("Bearing",R.drawable.pr_item_two));
        this.modelList.add(new ProductModel("Connecting Rod",R.drawable.pr_item_two));
        this.modelList.add(new ProductModel("Compensator",R.drawable.pr_item_two));
        this.modelList.add(new ProductModel("Others",R.drawable.pr_item_two));
*/
        adapter = new ProductAdapterDetails(PartforGasActivity.this, this.modelList);
        binding.recyclerProductDetails.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PartforGasActivity.this);
        binding.recyclerProductDetails.setLayoutManager(linearLayoutManager);
        binding.recyclerProductDetails.setAdapter(adapter);
        adapter.SetOnItemClickListener(new ProductAdapterDetails.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ProductDetailsModels.Result.PartsDetail model) {

              //  startActivity(new Intent(PartforGasActivity.this, ProductAddWeb.class).putExtra("Url","https://www.atlantic.com.pt/servi%C3%A7os/produto-teste/"));

            }
        });
    }

    private void GetProductDetailsMethod()
    {
        Call<ProductDetailsModels> call = RetrofitClients.getInstance().getApi()
                .Api_get_products_details(sessionManager.getProductId());
        call.enqueue(new Callback<ProductDetailsModels>() {
            @Override
            public void onResponse(Call<ProductDetailsModels> call, Response<ProductDetailsModels> response) {
                binding.progressBar.setVisibility(View.GONE);
                ProductDetailsModels finallyPr = response.body();
                if (finallyPr.getStatus().equalsIgnoreCase("1"))
                {
                    binding.RRitem.setVisibility(View.VISIBLE);
                    binding.txtName.setText(finallyPr.getResult().getProductName());
                    binding.txtToolName.setText(finallyPr.getResult().getProductName());

                    Glide.with(PartforGasActivity.this)
                            .load(finallyPr.getResult().getImage())
                            .placeholder(R.drawable.loader)
                            .into(binding.img);

                    modelList = (ArrayList<ProductDetailsModels.Result.PartsDetail>) finallyPr.getResult().getPartsDetail();

                    setAdapter(modelList);

                }else {
                    binding.RRitem.setVisibility(View.GONE);
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<ProductDetailsModels> call, Throwable t)
            {
                binding.RRitem.setVisibility(View.GONE);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

}