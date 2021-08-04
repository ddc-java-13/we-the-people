package edu.cnm.deepdive.wethepeople.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import java.util.Date;
import retrofit2.http.GET;


@Entity(
    tableName = "law_or_bill",
    indices = {
        @Index(value = "external_key", unique = true)
    }
)
public class LawOrBill {


  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "law_or_bill_id")
  private long id;

  @ColumnInfo(name = "external_key")
  @Expose
  @SerializedName("id")
  private String externalKey;

  //Need to understand what a CommentEndDate is
  //Below is last modified date - no data for creation date

  @Embedded(prefix = "attr_")
  private Attribute attribute;

  @ColumnInfo(name = "creation_date", index = true)
  @NonNull
  @Expose
  private Date creationDate;


  //Links to more information on the law
  @ColumnInfo(index = true, collate = ColumnInfo.NOCASE)
  @NonNull
  @Expose
  private Links links;


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
  public Links getLinks() {
    return links;
  }

  public void setLinks(@NonNull Links links) {
    this.links = links;
  }

  public static class Links {

    @Expose
    private String self;

    public Links(String self) {
      this.self = self;
    }

    @TypeConverter
    public static String toString(Links value) {
      return (value != null) ? value.getSelf() : null;
    }

    @TypeConverter
    public static Links from(String value) {
      return (value != null) ? new Links(value) : null;
    }



    public String getSelf() {
      return self;
    }

    public void setSelf(String self) {
      this.self = self;
    }
  }
}
