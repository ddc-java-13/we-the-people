package edu.cnm.deepdive.wethepeople.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.wethepeople.R;
import edu.cnm.deepdive.wethepeople.databinding.FragmentHomeBinding;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;
import edu.cnm.deepdive.wethepeople.viewmodel.MainViewModel;
import java.text.DateFormat;
import java.util.HashSet;
import java.util.Set;

public class HomeFragment extends Fragment {

  private MainViewModel mainViewModel;
  private FragmentHomeBinding binding;
  private LawOrBill item;
  private final Set<LawOrBill> items = new HashSet<>();
  @ColorInt
  private int bookmarkedItem;
  @ColorInt
  private int notBookmarkedItem;

  /**
   *
   * @param inflater inflates layout
   * @param container Gets the view group
   * @param savedInstanceState creates view Group
   * @return returns created view
   */


  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    Context context = getContext();
    bookmarkedItem = context.getColor(R.color.bookmarkedItem);
    notBookmarkedItem = context.getColor(R.color.notBookmarkedItem);
    binding.saveItem.setOnClickListener((v) -> mainViewModel.toggleBookmark(item));

    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    mainViewModel.getRandomReg().observe(getViewLifecycleOwner(), (randomReg) -> {
      item = randomReg;
      DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
      String formattedDate = getString(R.string.date_label,
          dateFormat.format(randomReg.getAttributes().getLastModifiedDate()));
      binding.title.setText(HtmlCompat.fromHtml(randomReg.getAttributes().getTitle(), 0));
      binding.lastModified.setText(formattedDate);
      binding.highlightedContent.setText(
          HtmlCompat.fromHtml(randomReg.getAttributes().getHighlightedContent(), 0));
      updateBookmark();
    });
    mainViewModel.getSaved().observe(getViewLifecycleOwner(), (items) -> {
      this.items.clear();
      this.items.addAll(items);
      updateBookmark();
    });
  }

  private void updateBookmark() {
    if (item != null) {
      binding.saveItem.setColorFilter(
          (items.contains(item))
              ? bookmarkedItem
              : notBookmarkedItem);
    }
  }

}