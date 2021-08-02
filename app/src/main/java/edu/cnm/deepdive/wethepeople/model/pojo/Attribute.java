package edu.cnm.deepdive.wethepeople.model.pojo;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class Attribute {

  @Expose
  private String documentType;

  @Expose
  private Date lastModifiedDate;

  @Expose
  private String highlightedContent;

  @Expose
  private String withdrawn;

  @Expose
  private String title;

  @Expose
  private String agencyId;



}
