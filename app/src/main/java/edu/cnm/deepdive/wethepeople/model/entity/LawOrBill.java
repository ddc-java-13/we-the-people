package edu.cnm.deepdive.wethepeople.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import java.util.Date;


@Entity(
    tableName = "law_or_bill"
)
public class LawOrBill {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "law_or_bill_id")
  private long id;

  @ColumnInfo(name = "external_key")
  @NonNull
  private String externalKey;

  @ColumnInfo(name = "creation_date", index = true)
  @NonNull
  private Date creationDate;

  @ColumnInfo(index = true, collate = ColumnInfo.NOCASE)
  @NonNull
  private String author;

  @ColumnInfo(index = true, collate = ColumnInfo.NOCASE)
  @NonNull
  private String agency;

  @ColumnInfo(index = true, collate = ColumnInfo.NOCASE)
  @NonNull
  private String signers;

  //type"bill or law"
  @ColumnInfo(index = true)
  @NonNull
  private Type type;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getExternalKey() {
    return externalKey;
  }

  public void setExternalKey(@NonNull String externalKey) {
    this.externalKey = externalKey;
  }

  @NonNull
  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(@NonNull Date creationDate) {
    this.creationDate = creationDate;
  }

  @NonNull
  public String getAuthor() {
    return author;
  }

  public void setAuthor(@NonNull String author) {
    this.author = author;
  }

  @NonNull
  public String getAgency() {
    return agency;
  }

  public void setAgency(@NonNull String agency) {
    this.agency = agency;
  }

  @NonNull
  public String getSigners() {
    return signers;
  }

  public void setSigners(@NonNull String signers) {
    this.signers = signers;
  }

  @NonNull
  public Type getType() {
    return type;
  }

  public void setType(@NonNull Type type) {
    this.type = type;
  }

  public enum Type {
    BILL, LAW;

    @TypeConverter
    public static Integer typeToInteger(Type value) {
      return (value != null) ? value.ordinal() : null;
    }

    @TypeConverter
    public static Type integerToType(Integer value) {
      return (value != null) ? Type.values()[value] : null;
    }
  }

}
