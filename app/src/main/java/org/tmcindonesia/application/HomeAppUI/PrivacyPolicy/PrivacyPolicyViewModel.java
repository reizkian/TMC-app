package org.tmcindonesia.application.HomeAppUI.PrivacyPolicy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrivacyPolicyViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PrivacyPolicyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}