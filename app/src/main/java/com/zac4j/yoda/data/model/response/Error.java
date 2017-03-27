package com.zac4j.yoda.data.model.response;

/**
 * Server Error Response.
 * Created by Zac on 2017/3/27.
 */

public class Error {

  private String error;
  private Integer errorCode;
  private String request;

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }
}
