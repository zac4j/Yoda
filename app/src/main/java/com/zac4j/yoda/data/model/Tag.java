package com.zac4j.yoda.data.model;

/**
 * Model for Tag
 * Created by zaccc on 6/1/2017.
 */

public class Tag {

  private String id;
  private String value;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tag tag = (Tag) o;

    if (id != null ? !id.equals(tag.id) : tag.id != null) return false;
    return value != null ? value.equals(tag.value) : tag.value == null;
  }

  @Override public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    return result;
  }
}
