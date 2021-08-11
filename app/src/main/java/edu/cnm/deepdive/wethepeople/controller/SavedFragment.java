package edu.cnm.deepdive.wethepeople.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.wethepeople.MobileNavigationDirections;
import edu.cnm.deepdive.wethepeople.adapter.SearchAndSavedAdapter;
import edu.cnm.deepdive.wethepeople.databinding.FragmentSavedBinding;
import edu.cnm.deepdive.wethepeople.viewmodel.MainViewModel;


public class SavedFragment extends Fragment {

  private MainViewModel viewModel;
  private FragmentSavedBinding binding;
  private SearchAndSavedAdapter adapter;

  /**
   *
   * @param inflater Item ViewModel
   * @param container contains View Group
   * @param savedInstanceState Saves Screen when cycling
   * @return returns created fragment
   */

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentSavedBinding.inflate(inflater, container, false);
    adapter = new SearchAndSavedAdapter(getContext(), true,
        (v, pos, item) -> viewModel.toggleBookmark(item),
        (v, pos, item) -> {
          viewModel.setItem(item);
          Navigation.findNavController(binding.getRoot())
              .navigate(MobileNavigationDirections.showDetails());
        });
    binding.searchResults.setAdapter(adapter);

    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    getLifecycle().addObserver(viewModel);
    viewModel.getSaved().observe(getViewLifecycleOwner(), (items) -> {
      adapter.getItems().clear();
      adapter.getItems().addAll(items);
      adapter.notifyDataSetChanged();
    });


  }

}