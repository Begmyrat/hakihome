package com.begmyratmammedov.hakihome.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.begmyratmammedov.hakihome.model.ModelCategory;
import com.begmyratmammedov.hakihome.model.ModelProduct;

import java.util.ArrayList;
import java.util.List;

public class FragmentHomeViewModel extends ViewModel {
    MutableLiveData<List<ModelCategory>> categories = new MutableLiveData<>();
    MutableLiveData<Boolean> isCategoriesLoading = new MutableLiveData<>();
    MutableLiveData<Boolean> isCategoriesError = new MutableLiveData<>();
    MutableLiveData<List<ModelProduct>> products = new MutableLiveData<>();
    MutableLiveData<Boolean> isProductsLoading = new MutableLiveData<>();
    MutableLiveData<Boolean> isProductsError = new MutableLiveData<>();

    public MutableLiveData<List<ModelProduct>> getProducts() {
        return products;
    }

    public void setProducts(List<ModelProduct> products) {
        this.products.postValue(products);
    }

    public MutableLiveData<List<ModelCategory>> getCategories() {
        return categories;
    }

    public void setCategories(List<ModelCategory> categories) {
        this.categories.postValue(categories);
    }

    public void setCategories(MutableLiveData<List<ModelCategory>> categories) {
        this.categories = categories;
    }

    public MutableLiveData<Boolean> getIsCategoriesLoading() {
        return isCategoriesLoading;
    }

    public void setIsCategoriesLoading(MutableLiveData<Boolean> isCategoriesLoading) {
        this.isCategoriesLoading = isCategoriesLoading;
    }

    public MutableLiveData<Boolean> getIsCategoriesError() {
        return isCategoriesError;
    }

    public void setIsCategoriesError(MutableLiveData<Boolean> isCategoriesError) {
        this.isCategoriesError = isCategoriesError;
    }

    public void getCategoriesFromDB(){

        isCategoriesLoading.postValue(true);

        List<ModelCategory> list = new ArrayList<>();
        list.add(new ModelCategory("All"));
        list.add(new ModelCategory("Kitchen"));
        list.add(new ModelCategory("Bathroom"));
        list.add(new ModelCategory("Home"));
        list.add(new ModelCategory("Furniture"));

        isCategoriesLoading.postValue(false);
        isCategoriesError.postValue(false);
        categories.postValue(list);
    }

    public void getProductsFromDB(){

        isProductsLoading.postValue(true);

        List<ModelProduct> list = new ArrayList<>();
        list.add(new ModelProduct("Irul Chair", "%50 sale", "Ergonomical for humans body curve","102","https://tarikkaraca.com/wp-content/uploads/2020/09/urun-milano-koltuk-takimi-01-1-1536x1024.jpg"));
        list.add(new ModelProduct("Irul Chair", "%50 sale", "Ergonomical for humans body curve","102","https://tarikkaraca.com/wp-content/uploads/2020/09/urun-milano-koltuk-takimi-01-1-1536x1024.jpg"));
        list.add(new ModelProduct("Irul Chair", "%50 sale", "Ergonomical for humans body curve","102","https://tarikkaraca.com/wp-content/uploads/2020/09/urun-milano-koltuk-takimi-01-1-1536x1024.jpg"));
        list.add(new ModelProduct("Irul Chair", "%50 sale", "Ergonomical for humans body curve","102","https://tarikkaraca.com/wp-content/uploads/2020/09/urun-milano-koltuk-takimi-01-1-1536x1024.jpg"));

        isProductsLoading.postValue(false);
        isProductsError.postValue(false);
        products.postValue(list);
    }
}
