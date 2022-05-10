package com.my.asp.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.my.asp.Fragment.ProductFragment;
import com.my.asp.R;;
import com.my.asp.databinding.ActivityNavigationBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeActivity extends AppCompatActivity {

    ActivityNavigationBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_navigation);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_Product, R.id.navigation_home, R.id.navigation_Contacts, R.id.navigation_notifications)
                .build();
         navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        NavigationUI.setupWithNavController(binding.home.navView, navController);

        binding.home.RRsyn.setOnClickListener(v -> {

            ProductFragment fragment = (ProductFragment) getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().getFragments().get(0);
            fragment.GetProductMethod();

            }
        );

        binding.home.RRNav.setOnClickListener(v -> {
            if( binding.drawer.isDrawerOpen(GravityCompat.START)) {
                binding.drawer.closeDrawer(Gravity.LEFT); //CLOSE Nav Drawer!
            }else{
                binding.drawer.openDrawer(Gravity.LEFT); //OPEN Nav Drawer!
            }
        });

        binding.nav.imgBack.setOnClickListener(v -> {
            binding.drawer.closeDrawer(Gravity.LEFT);
        });

        binding.nav.txtProduct.setOnClickListener(v -> {

            if( binding.drawer.isDrawerOpen(GravityCompat.START)) {
                binding.drawer.closeDrawer(Gravity.LEFT); //CLOSE Nav Drawer!
            }else{
                binding.drawer.openDrawer(Gravity.LEFT); //OPEN Nav Drawer!
            }
            binding.home.RRsyn.setVisibility(View.VISIBLE);
            binding.home.txtToolName.setText("Products");

            navController.navigateUp();
            navController.navigate(R.id.navigation_Product);

        });

        binding.nav.txtAbouts.setOnClickListener(v -> {

            if( binding.drawer.isDrawerOpen(GravityCompat.START)) {
                binding.drawer.closeDrawer(Gravity.LEFT); //CLOSE Nav Drawer!
            }else{
                binding.drawer.openDrawer(Gravity.LEFT); //OPEN Nav Drawer!
            }
            binding.home.RRsyn.setVisibility(View.GONE);
          //  binding.home.txtToolName.setText("Abouts");

          //  startActivity(new Intent(HomeActivity.this, ProductAddWeb.class).putExtra("Url","https://www.atlantic.com.pt/contacto/"));
            binding.home.txtToolName.setText("Product");
           navController.navigateUp();
            navController.navigate(R.id.action_navigation_home_to_navigation_Product);

        });

        binding.nav.txtNews.setOnClickListener(v -> {

            if( binding.drawer.isDrawerOpen(GravityCompat.START)) {
                binding.drawer.closeDrawer(Gravity.LEFT); //CLOSE Nav Drawer!
            }else{
                binding.drawer.openDrawer(Gravity.LEFT); //OPEN Nav Drawer!
            }

            binding.home.RRsyn.setVisibility(View.GONE);
            binding.home.txtToolName.setText("News");

            navController.navigateUp();
            navController.navigate(R.id.news);

        });

      binding.nav.txtContact.setOnClickListener(v -> {

            if( binding.drawer.isDrawerOpen(GravityCompat.START)) {
                binding.drawer.closeDrawer(Gravity.LEFT); //CLOSE Nav Drawer!
            }else{
                binding.drawer.openDrawer(Gravity.LEFT); //OPEN Nav Drawer!
            }

            binding.home.RRsyn.setVisibility(View.GONE);
            binding.home.txtToolName.setText("Contacts");

             navController.navigateUp();
             navController.navigate(R.id.navigation_Contacts);

        });


        binding.home.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               binding.home.navView.getMenu().setGroupCheckable(0, true, true);
               switch (item.getItemId())
                {
                   case R.id.navigation_home : navController.navigate(R.id.navigation_home);
                       binding.home.txtToolName.setText("Home");
                       binding.home.RRsyn.setVisibility(View.GONE);
                        break;
                    case R.id.navigation_Product : navController.navigate(R.id.navigation_Product);
                        binding.home.txtToolName.setText("Products");
                        binding.home.RRsyn.setVisibility(View.VISIBLE);
                        break;
                    case R.id.navigation_Contacts : navController.navigate(R.id.navigation_Contacts);
                        binding.home.txtToolName.setText("Contacts");
                        binding.home.RRsyn.setVisibility(View.GONE);
                        break;
                    case R.id.navigation_notifications : navController.navigate(R.id.navigation_notifications);
                        binding.home.txtToolName.setText("NOTIFICATIONS");
                        binding.home.RRsyn.setVisibility(View.GONE);
                }
                return true;
           }
        });

      onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent.getStringExtra("Type").equals("news"))
        {
            binding.home.txtToolName.setText("News");
            binding.home.RRsyn.setVisibility(View.GONE);
            navController.navigateUp();
            navController.navigate(R.id.news);

        }else if(intent.getStringExtra("Type").equals("Product"))
        {
            binding.home.txtToolName.setText("Product");
            binding.home.RRsyn.setVisibility(View.GONE);
            navController.navigateUp();
            navController.navigate(R.id.navigation_Product);
        }
    }

    public void setData(String title)
    {
        binding.home.txtToolName.setText(title);
    }

}