package edu.cnm.deepdive.wethepeople.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import java.util.Date;

public class Attributes {

  /**
   * Holds pertinent Data from the service.
   * Highlighted Content is the body of the collected data
   */
  @Expose
  @ColumnInfo(name = "document_type")
  private String documentType;

  @Expose
  @ColumnInfo(name = "last_modified_date")
  private Date lastModifiedDate;

  @Expose
  @ColumnInfo(name = "highlighted_content")
  private String highlightedContent;

  @Expose
  private boolean withdrawn;

  @Expose
  private String title;

  @Expose
  @ColumnInfo(name = "agency_id")
  private String agencyId;


  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getHighlightedContent() {
    return highlightedContent;
  }

  public void setHighlightedContent(String highlightedContent) {
    this.highlightedContent = highlightedContent;
  }


  public boolean isWithdrawn() {
    return withdrawn;
  }

  public void setWithdrawn(boolean withdrawn) {
    this.withdrawn = withdrawn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAgencyId() {
    return agencyId;
  }

  public void setAgencyId(String agencyId) {
    this.agencyId = agencyId;
  }


}
