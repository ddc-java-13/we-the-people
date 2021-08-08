package edu.cnm.deepdive.wethepeople.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.wethepeople.R;
import edu.cnm.deepdive.wethepeople.adapter.SearchAdapter.Holder;
import edu.cnm.deepdive.wethepeople.databinding.ItemSearchBinding;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchAdapter extends RecyclerView.Adapter<Holder> {

  private final List<LawOrBill> items;
  private final OnSaveClickListener listener;
  private final LayoutInflater inflater;
  private final DateFormat dateFormat;
  private final String dateLabel;
  private final Set<LawOrBill> bookmarks;
  @ColorInt
  private final int bookmarkedItem;
  @ColorInt
  private final int notBookmarkedItem;

  public SearchAdapter(@NonNull Context context, OnSaveClickListener listener) {
    this.listener = listener;
    inflater = LayoutInflater.from(context);
    dateFormat = android.text.format.DateFormat.getDateFormat(context);
    dateLabel = context.getString(R.string.date_label);
    items = new ArrayList<>();
    bookmarks = new HashSet<>();
    bookmarkedItem = context.getColor(R.color.bookmarkedItem);
    notBookmarkedItem = context.getColor(R.color.notBookmarkedItem);

  }

  public List<LawOrBill> getItems() {
    return items;
  }

  public Set<LawOrBill> getBookmarks() {
    return bookmarks;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new Holder(ItemSearchBinding.inflate(inflater, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final ItemSearchBinding binding;

    private Holder(@NonNull ItemSearchBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      LawOrBill item = items.get(position);
      binding.title.setText(item.getAttributes().getTitle());
      binding.lastModified.setText(String.format(dateLabel,
          dateFormat.format(item.getAttributes().getLastModifiedDate())));
      binding.saveItem.setColorFilter(
          bookmarks.contains(item) ? bookmarkedItem : notBookmarkedItem);
      binding.saveItem.setOnClickListener((v) ->
          listener.onSaveClick(binding.saveItem, position, item));
    }
  }

  @FunctionalInterface
  public interface OnSaveClickListener {

    void onSaveClick (View view, int position, LawOrBill item);
  }
}
