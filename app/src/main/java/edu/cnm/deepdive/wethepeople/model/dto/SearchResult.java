package edu.cnm.deepdive.wethepeople.model.dto;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.wethepeople.model.entity.LawOrBill;
import java.util.Arrays;

//Search Class
public class SearchResult {

  @Expose
  LawOrBill[] data;

  public LawOrBill[] getData() {
    return data;
  }

  public void setData(LawOrBill[] data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "SearchResult{" + "data=" + Arrays.toString(getData());
  }


}
