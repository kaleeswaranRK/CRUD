package com.ta.operations.model;

public class DBModel {

	  private long id;
	  private String title;
	  private String descriptions;
	  private boolean published;
	  public DBModel() {

	  }
	  
	  public DBModel(long id, String title, String descriptions, boolean published) {
	    this.id = id;
	    this.title = title;
	    this.descriptions = descriptions;
	    this.published = published;
	  }

	  public DBModel(String title, String descriptions, boolean published) {
	    this.title = title;
	    this.descriptions = descriptions;
	    this.published = published;
	  }
	  
	  public void setId(long id) {
	    this.id = id;
	  }
	  
	  public long getId() {
	    return id;
	  }

	  public String getTitle() {
	    return title;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }

	  public String getDescriptions() {
	    return descriptions;
	  }

	  public void setDescriptions(String descriptions) {
	    this.descriptions = descriptions;
	  }

	  public boolean isPublished() {
	    return published;
	  }

	  public void setPublished(boolean isPublished) {
	    this.published = isPublished;
	  }

	  @Override
	  public String toString() {
	    return "DBModel [id=" + id + ", title=" + title + ", desc=" + descriptions + ", published=" + published + "]";
	  }

	}