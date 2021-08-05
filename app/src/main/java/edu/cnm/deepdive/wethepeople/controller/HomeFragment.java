package edu.cnm.deepdive.wethepeople.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.wethepeople.R;
import edu.cnm.deepdive.wethepeople.databinding.FragmentHomeBinding;
import edu.cnm.deepdive.wethepeople.viewmodel.HomeViewModel;
import java.text.DateFormat;

public class HomeFragment extends Fragment {

  private HomeViewModel homeViewModel;
  private FragmentHomeBinding binding;


  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
    homeViewModel.getRandomReg().observe(getViewLifecycleOwner(), (randomReg) -> {
      DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
      String formattedDate = getString(R.string.date_label, dateFormat.format(randomReg.getAttributes().getLastModifiedDate()));
      binding.title.setText(HtmlCompat.fromHtml(randomReg.getAttributes().getTitle(), 0));
      binding.lastModified.setText(formattedDate);
      binding.highlightedContent.setText(HtmlCompat.fromHtml(randomReg.getAttributes().getHighlightedContent(), 0));
    });
  }
}