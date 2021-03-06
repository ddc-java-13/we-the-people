package edu.cnm.deepdive.wethepeople.model.dto;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;
import java.util.Arrays;
import java.util.List;


public class SearchResult {

  /**
   * Search Code gets data through key terms
   */
  @Expose
  List<LawOrBill> data;

  public List<LawOrBill> getData() {
    return data;
  }

  public void setData(List<LawOrBill> data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "SearchResult{" + "data=" + getData();
  }


}
