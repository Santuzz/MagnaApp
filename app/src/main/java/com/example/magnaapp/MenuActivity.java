package com.example.magnaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.magnaapp.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        setContentView(binding.getRoot());

        new MenuFragment();

        binding.bottomNavigationBar.setOnClickListener(item -> {
            switch(item.getId()){
                case R.id.storage:
                    replaceFragment(new StorageFragment());
                    break;
                case R.id.menu:
                    replaceFragment(new MenuFragment());
                    break;
                case R.id.past_orders:
                    replaceFragment(new PastOrdersFragment());
                    break;
                case R.id.settings:
                    replaceFragment(new SettingsFragment());
                    break;
            }

        });

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}