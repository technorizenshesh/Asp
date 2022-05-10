package com.my.asp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.my.asp.act.HomeActivity;
import com.my.asp.databinding.ActivityMainBinding;
import com.my.asp.model.AddNotificationToken;
import com.my.asp.utils.RetrofitClients;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private String token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(runnable -> {
            token = runnable.getToken();
            AddTokenMethod(token);
            Log.e( "Tokennnn>>>>" ,token);
        });

        handlerMethod();
     }

    private void handlerMethod() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("Type","Home");
                startActivity(intent);
                finish();

            }
        }, 3000);
    }


    private void AddTokenMethod(String tokens){
        Call<ResponseBody> call = RetrofitClients.getInstance().getApi()
                .Api_add_registor(tokens);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String Value = response.body().string();
                    AddNotificationToken finallyPr = new Gson().fromJson(Value, AddNotificationToken.class);
                    String status = finallyPr.getStatus();
                    if(status.equalsIgnoreCase("1"))
                    {
                       // Toast.makeText(MainActivity.this, ""+finallyPr.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception e)
                {
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}