package edu.cnm.deepdive.wethepeople.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.wethepeople.R;
import edu.cnm.deepdive.wethepeople.adapter.SearchAdapter;
import edu.cnm.deepdive.wethepeople.databinding.FragmentSearchBinding;
import edu.cnm.deepdive.wethepeople.model.dto.SearchResult;
import edu.cnm.deepdive.wethepeople.model.entity.Attributes;
import edu.cnm.deepdive.wethepeople.viewmodel.SearchViewModel;


public class SearchFragment extends Fragment {

  private SearchViewModel searchViewModel;
  private FragmentSearchBinding binding;
  private SearchAdapter adapter;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentSearchBinding.inflate(inflater, container, false);
    binding.search.setOnClickListener((v) ->
        searchViewModel.search(binding.searchTerm.getText().toString().trim()));
    adapter = new SearchAdapter(getContext(),
        (v, pos, item) -> { /* TODO Ask view model to toggle item as a bookmark */});
    binding.searchResults.setAdapter(adapter);

    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    searchViewModel = new ViewModelProvider(getActivity()).get(SearchViewModel.class);
    getLifecycle().addObserver(searchViewModel);
    searchViewModel.getSearchResults().observe(getViewLifecycleOwner(), (searchResults) -> {
      adapter.getItems().clear();
      adapter.getItems().addAll(searchResults);
      adapter.notifyDataSetChanged();
    });
    searchViewModel.getBookmarkedSet().observe(getViewLifecycleOwner(), (bookmarks) -> {
      adapter.getBookmarks().addAll(bookmarks);
      adapter.notifyDataSetChanged();
    });


  }
}
