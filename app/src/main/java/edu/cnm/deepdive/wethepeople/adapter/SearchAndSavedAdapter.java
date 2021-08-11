package edu.cnm.deepdive.wethepeople.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.wethepeople.R;
import edu.cnm.deepdive.wethepeople.adapter.SearchAndSavedAdapter.Holder;
import edu.cnm.deepdive.wethepeople.databinding.ItemSearchBinding;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *
 */
public class SearchAndSavedAdapter extends RecyclerView.Adapter<Holder> {

  private final List<LawOrBill> items;
  private final boolean savedAssumed;
  private final OnSaveClickListener saveListener;
  private final OnItemClickListener itemListener;
  private final LayoutInflater inflater;
  private final DateFormat dateFormat;
  private final String dateLabel;
  private final Set<LawOrBill> bookmarks;
  @ColorInt
  private final int bookmarkedItem;
  @ColorInt
  private final int notBookmarkedItem;

  /**
   *
   * @param context refers to color if saved vs. non saved
   * @param savedAssumed Checks to see if item is saved in database
   * @param saveListener looks for saved item
   * @param itemListener finds item in database
   */

  public SearchAndSavedAdapter(@NonNull Context context, boolean savedAssumed,
      OnSaveClickListener saveListener, OnItemClickListener itemListener) {
    this.savedAssumed = savedAssumed;
    this.saveListener = saveListener;
    this.itemListener = itemListener;
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
          (savedAssumed || bookmarks.contains(item))
              ? bookmarkedItem
              : notBookmarkedItem);
      binding.saveItem.setOnClickListener((v) ->
          saveListener.onSaveClick(binding.saveItem, position, item));
      binding.getRoot().setOnClickListener((v) ->
          itemListener.onItemClick(binding.getRoot(), position, item));
    }
  }

  @FunctionalInterface
  public interface OnSaveClickListener {

    void onSaveClick(View view, int position, LawOrBill item);
  }

  @FunctionalInterface
  public interface OnItemClickListener {

    void onItemClick(View view, int position, LawOrBill item);

  }
}
