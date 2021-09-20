package com.begmyratmammedov.hakihome.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    MutableLiveData<Integer> currentFragmentIndex = new MutableLiveData<>();
    MutableLiveData<Boolean> isBlueEnabled = new MutableLiveData<>();

    public MutableLiveData<Integer> getCurrentFragmentIndex() {
        return currentFragmentIndex;
    }

    public void setCurrentFragmentIndex(MutableLiveData<Integer> currentFragmentIndex) {
        this.currentFragmentIndex = currentFragmentIndex;
    }

    public MutableLiveData<Boolean> getIsBlueEnabled() {
        return isBlueEnabled;
    }

    public void setIsBlueEnabled(Boolean isBlueEnabled) {
        this.isBlueEnabled.postValue(isBlueEnabled);
    }

    public MutableLiveData<Integer> getCurrentFragment() {
        return currentFragmentIndex;
    }

    public void setCurrentFragment(Integer currentFragment) {
        if(currentFragment==0)
            isBlueEnabled.postValue(true);
        else
            isBlueEnabled.postValue(false);
        this.currentFragmentIndex.postValue(currentFragment);
    }
}
