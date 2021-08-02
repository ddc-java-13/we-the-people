package edu.cnm.deepdive.wethepeople.controller.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.wethepeople.R;
import edu.cnm.deepdive.wethepeople.viewmodel.SearchViewModel;


public class SearchFragment extends Fragment {

  private SearchViewModel searchViewModel;


  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    searchViewModel =
        new ViewModelProvider(this).get(SearchViewModel.class);
    View root = inflater.inflate(R.layout.fragment_search, container, false);
    final TextView textView = root.findViewById(R.id.search);
    searchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    return root;
  }
}