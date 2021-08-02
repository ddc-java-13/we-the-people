package edu.cnm.deepdive.wethepeople.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.wethepeople.model.pojo.Attribute;
import java.util.Arrays;
import java.util.Date;


@Entity(
    tableName = "law_or_bill"
)
public class LawOrBill {


  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "law_or_bill_id")
  @Expose
  private long id;

  //Need to understand what a CommentEndDate is
  //Below is last modified date - no data for creation date

  private Attribute attribute;

  @ColumnInfo(name = "creation_date", index = true)
  @NonNull
  @Expose
  private Date creationDate;


  //Links to more information on the law
  @ColumnInfo(index = true, collate = ColumnInfo.NOCASE)
  @NonNull
  @Expose
  private String links;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Attribute getAttribute() {
    return attribute;
  }

  public void setAttribute(Attribute attribute) {
    this.attribute = attribute;
  }

  @NonNull
  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(@NonNull Date creationDate) {
    this.creationDate = creationDate;
  }

  @NonNull
  public String getLinks() {
    return links;
  }

  public void setLinks(@NonNull String links) {
    this.links = links;
  }

  //Search Class
  public static class SearchResult {

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

}
