package edu.cnm.deepdive.wethepeople.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.wethepeople.R;
import edu.cnm.deepdive.wethepeople.viewmodel.SavedViewModel;

public class SavedFragment extends Fragment {

  private SavedViewModel savedViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    savedViewModel =
        new ViewModelProvider(this).get(SavedViewModel.class);
    View root = inflater.inflate(R.layout.fragment_saved_objects, container, false);
    return root;
  }
}