package edu.cnm.deepdive.wethepeople.controller;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.wethepeople.R;
import edu.cnm.deepdive.wethepeople.databinding.FragmentDetailsBinding;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;
import edu.cnm.deepdive.wethepeople.viewmodel.MainViewModel;
import java.text.DateFormat;
import java.util.HashSet;
import java.util.Set;

public class DetailsFragment extends DialogFragment {

  private MainViewModel viewModel;
  private FragmentDetailsBinding binding;
  private Dialog dialog;
  private String dateLabel;
  private DateFormat dateFormat;
  private LawOrBill item;
  private final Set<LawOrBill> items = new HashSet<>();
  @ColorInt
  private int bookmarkedItem;
  @ColorInt
  private int notBookmarkedItem;

  /**
   *
   * @param savedInstanceState Bookmarks a saved Item
   * @return a dialog Interface
   */
  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    Context context = getContext();
    dateLabel = context.getString(R.string.date_label);
    dateFormat = android.text.format.DateFormat.getDateFormat(context);
    bookmarkedItem = context.getColor(R.color.bookmarkedItem);
    notBookmarkedItem = context.getColor(R.color.notBookmarkedItem);
    binding = FragmentDetailsBinding.inflate(LayoutInflater.from(context), null, false);
    binding.saveItem.setOnClickListener((v) -> viewModel.toggleBookmark(item));
    dialog = new AlertDialog.Builder(context)
        .setTitle("Details")
        .setView(binding.getRoot())
        .setNeutralButton(android.R.string.ok, null)
        .create();
    return dialog;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    viewModel.getItem().observe(getViewLifecycleOwner(), (item) -> {
      this.item = item;
      binding.title.setText(item.getAttributes().getTitle());
      binding.highlightedContent.setText(
          HtmlCompat.fromHtml(item.getAttributes().getHighlightedContent(),
              HtmlCompat.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL | HtmlCompat.FROM_HTML_MODE_LEGACY));
      binding.lastModified.setText(
          String.format(dateLabel, dateFormat.format(item.getAttributes().getLastModifiedDate())));
      updateBookmark();
    });
    viewModel.getSaved().observe(getViewLifecycleOwner(), (items) -> {
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
