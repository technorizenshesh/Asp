package com.my.asp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.my.asp.MainActivity;
import com.my.asp.R;
import com.my.asp.act.HomeActivity;
import com.my.asp.adapter.CountryListAdapter;
import com.my.asp.databinding.FragmentContactBinding;
import com.my.asp.model.ContactModels;
import com.my.asp.model.CountryListModels;
import com.my.asp.model.TrackOrderModels;
import com.my.asp.utils.RetrofitClients;
import com.my.asp.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContactFragment extends Fragment {

    FragmentContactBinding binding;
    private SessionManager sessionManager;
    private ArrayList<CountryListModels.Result> modelListCategory = new ArrayList<>();
    private String CountryId ="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_contact, container, false);

        sessionManager = new SessionManager(getActivity());

        binding.RRAddContacts.setOnClickListener(v -> {

            if(binding.edtNAme.getText().toString().equalsIgnoreCase(""))
            {
                Toast.makeText(getActivity(), "PLease Enter Name", Toast.LENGTH_SHORT).show();

            }else if(binding.edtDapartment.getText().toString().equalsIgnoreCase(""))
            {
                Toast.makeText(getActivity(), "PLease Enter DepartMent", Toast.LENGTH_SHORT).show();

            }else if(binding.edtEmail.getText().toString().equalsIgnoreCase(""))
            {
                Toast.makeText(getActivity(), "PLease Enter Email", Toast.LENGTH_SHORT).show();

            }else if(binding.edtSubject.getText().toString().equalsIgnoreCase(""))
            {
                Toast.makeText(getActivity(), "PLease Enter Subject", Toast.LENGTH_SHORT).show();

            }else if(binding.edtMessage.getText().toString().equalsIgnoreCase(""))
            {
                Toast.makeText(getActivity(), "PLease Enter MEssage", Toast.LENGTH_SHORT).show();

            }else {

                if (sessionManager.isNetworkAvailable()) {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    apiContactMethod();
                }else {
                    Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
                }

            }
        });

        if (sessionManager.isNetworkAvailable()) {
            binding.progressBar.setVisibility(View.VISIBLE);
            GetCountyListMethod();
        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }

        binding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3){

                CountryId = modelListCategory.get(pos).getId();

                if(CountryId.equalsIgnoreCase("1"))
                {
                    binding.txtLocation.setText("Industrial Vila Amelia, Lot 602 2950-805 / Vila Amelia - Quinta do Anjo Portugal");
                    binding.txtPhn.setText("+351 210 935 394");

                }else if(CountryId.equalsIgnoreCase("2"))
                {
                    binding.txtLocation.setText("San Pedro Sula - Hunduras, 153");
                    binding.txtPhn.setText("+504 3184 2328");
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        return binding.getRoot();
    }

    private void apiContactMethod()
    {
        binding.progressBar.setVisibility(View.VISIBLE);
        Call<ContactModels> call = RetrofitClients.getInstance().getApi()
                .Api_contact(binding.edtNAme.getText().toString(),"1","75.00","75.00","78655",binding.edtEmail.getText().toString(),
                        binding.edtDapartment.getText().toString(),binding.edtSubject.getText().toString(),binding.edtMessage.getText().toString(),"indore");
        call.enqueue(new Callback<ContactModels>() {
            @Override
            public void onResponse(Call<ContactModels> call, Response<ContactModels> response) {
                binding.progressBar.setVisibility(View.GONE);
                ContactModels finallyPr = response.body();
                if (finallyPr.getStatus().equalsIgnoreCase("1"))
                {
                    Toast.makeText(getActivity(), "Success.", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getActivity(), HomeActivity.class);
                    intent.putExtra("Type","Home");
                    startActivity(intent);

                }else {
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<ContactModels> call, Throwable t)
            {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }



    private void GetCountyListMethod()
    {
        Call<CountryListModels> call = RetrofitClients.getInstance().getApi()
                .Api_country_list();
        call.enqueue(new Callback<CountryListModels>() {
            @Override
            public void onResponse(Call<CountryListModels> call, Response<CountryListModels> response) {

                binding.progressBar.setVisibility(View.GONE);

                CountryListModels finallyPr = response.body();

                if (finallyPr.getStatus().equalsIgnoreCase("1"))
                {
                    modelListCategory = (ArrayList<CountryListModels.Result>) finallyPr.getResult();

                    CountryListAdapter customAdapter=new CountryListAdapter(getActivity(),modelListCategory);
                    binding.spinnerCategory.setAdapter(customAdapter);

                }else {

                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), finallyPr.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CountryListModels> call, Throwable t)
            {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}