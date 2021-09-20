package com.begmyratmammedov.hakihome.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.begmyratmammedov.hakihome.R;
import com.begmyratmammedov.hakihome.adapters.ViewPagerFragmentAdapter;
import com.begmyratmammedov.hakihome.databinding.ActivityMainBinding;
import com.begmyratmammedov.hakihome.fragments.FragmentFavourites;
import com.begmyratmammedov.hakihome.fragments.FragmentHome;
import com.begmyratmammedov.hakihome.fragments.FragmentProfile;
import com.begmyratmammedov.hakihome.fragments.FragmentSearch;
import com.begmyratmammedov.hakihome.viewmodel.MainActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    View view;
    MainActivityViewModel viewModel;
    ArrayList<Fragment> fragments;
    ViewPagerFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        prepareMe();

        observe();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
    }
    

    private void prepareMe() {

        fragments = new ArrayList<>();
        fragments.add(new FragmentHome());
        fragments.add(new FragmentSearch());
        fragments.add(new FragmentFavourites());
        fragments.add(new FragmentProfile());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.setCurrentFragment(0);
        adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setUserInputEnabled(false);
        binding.viewPager.setOffscreenPageLimit(4);
        binding.bottomBar.setOnNavigationItemSelectedListener(this);
    }

    private void observe() {
        viewModel.getCurrentFragment().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.viewPager.setCurrentItem(integer, false);
            }
        });

        viewModel.getIsBlueEnabled().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    binding.iBlur.setVisibility(View.VISIBLE);
                }
                else{
                    binding.iBlur.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                viewModel.setCurrentFragment(0);
                break;
            case R.id.search:
                viewModel.setCurrentFragment(1);
                break;
            case R.id.favourite:
                viewModel.setCurrentFragment(2);
                break;
            case R.id.profile:
                viewModel.setCurrentFragment(3);
                break;
        }

        return true;
    }

    public void clickHome(View view) {
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        viewModel.setCurrentFragment(0);
    }

    public void clickSearch(View view) {
        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
        viewModel.setCurrentFragment(1);
    }

    public void clickBag(View view) {
        Toast.makeText(this, "Bag", Toast.LENGTH_SHORT).show();
        viewModel.setCurrentFragment(2);
    }

    public void clickProfile(View view) {
        Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        viewModel.setCurrentFragment(3);
    }
}