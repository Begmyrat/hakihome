package com.begmyratmammedov.hakihome.fragments;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.RadioAccessSpecifier;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.begmyratmammedov.hakihome.R;
import com.begmyratmammedov.hakihome.adapters.MyCategoriesListAdapter;
import com.begmyratmammedov.hakihome.adapters.MyProductListAdapter;
import com.begmyratmammedov.hakihome.databinding.FragmentHomeBinding;
import com.begmyratmammedov.hakihome.model.ModelCategory;
import com.begmyratmammedov.hakihome.model.ModelProduct;
import com.begmyratmammedov.hakihome.view.MainActivity;
import com.begmyratmammedov.hakihome.viewmodel.FragmentHomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements MyCategoriesListAdapter.ItemClickListener, MyProductListAdapter.ItemClickListenerProducts {

    View view;
    MainActivity activity;
    FragmentHomeBinding binding;
    FragmentHomeViewModel viewModel;
    List<ModelCategory> categoryList;
    List<ModelProduct> productList;
    MyCategoriesListAdapter adapter;
    MyProductListAdapter adapterProducts;
    RecyclerView.LayoutManager layoutManager, layoutManagerVertical;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        activity = (MainActivity) getActivity();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        prepareMe();

        observe();

        return view;
    }

    private void observe() {
        viewModel.getCategories().observe(activity, new Observer<List<ModelCategory>>() {
            @Override
            public void onChanged(List<ModelCategory> modelCategories) {
                categoryList.addAll(modelCategories);
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.getProducts().observe(activity, new Observer<List<ModelProduct>>() {
            @Override
            public void onChanged(List<ModelProduct> modelProducts) {
                productList.addAll(modelProducts);
                adapterProducts.notifyDataSetChanged();
            }
        });
    }

    private void prepareMe() {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.colorBackground));// set status background white

        categoryList = new ArrayList<>();
        productList = new ArrayList<>();
        viewModel = new ViewModelProvider(activity).get(FragmentHomeViewModel.class);
        viewModel.getCategoriesFromDB();
        viewModel.getProductsFromDB();
        adapter = new MyCategoriesListAdapter(activity, categoryList);
        adapterProducts = new MyProductListAdapter(activity, productList);
        binding.recyclerviewCategories.setAdapter(adapter);
        binding.recyclerviewResults.setAdapter(adapterProducts);
        layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        layoutManagerVertical = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        binding.recyclerviewCategories.setLayoutManager(layoutManager);
        binding.recyclerviewResults.setLayoutManager(layoutManagerVertical);
        adapter.setClickListener(this);
        adapterProducts.setClickListenerProducts(this);

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(activity, "pos: " + categoryList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        adapter.setSelectedItem(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickProducts(View view, int position) {
        Toast.makeText(activity, "pos: " + position, Toast.LENGTH_SHORT).show();
    }
}