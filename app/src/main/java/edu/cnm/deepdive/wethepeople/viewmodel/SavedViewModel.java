package edu.cnm.deepdive.wethepeople.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
//TODO Make shareable
public class SavedViewModel extends ViewModel {


  private MutableLiveData<String> mText;

  public SavedViewModel() {

    mText = new MutableLiveData<>();
    mText.setValue("This is a saved fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}