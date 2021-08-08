package edu.cnm.deepdive.wethepeople.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.wethepeople.adapter.SearchAndSavedAdapter;
import edu.cnm.deepdive.wethepeople.databinding.FragmentSearchBinding;
import edu.cnm.deepdive.wethepeople.viewmodel.MainViewModel;


public class SearchFragment extends Fragment {

  private MainViewModel mainViewModel;
  private FragmentSearchBinding binding;
  private SearchAndSavedAdapter adapter;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentSearchBinding.inflate(inflater, container, false);
    binding.search.setOnClickListener((v) ->
        mainViewModel.search(binding.searchTerm.getText().toString().trim()));
    adapter = new SearchAndSavedAdapter(getContext(), false,
        (v, pos, item) -> mainViewModel.toggleBookmark(item),
        (v, pos, item) -> { /* TODO display item details */});
    binding.searchResults.setAdapter(adapter);

    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    getLifecycle().addObserver(mainViewModel);
    mainViewModel.getSearchResults().observe(getViewLifecycleOwner(), (searchResults) -> {
      adapter.getItems().clear();
      adapter.getItems().addAll(searchResults);
      adapter.notifyDataSetChanged();
    });
    mainViewModel.getSaved().observe(getViewLifecycleOwner(), (bookmarks) -> {
      adapter.getBookmarks().clear();
      adapter.getBookmarks().addAll(bookmarks);
      adapter.notifyDataSetChanged();
    });


  }
}
