package com.wxz.wiki.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class PageReq {
  @NotNull(message = "[current page] cannot be null")
  private int page;

  @NotNull(message = "[page size] cannot be null")
  @Max(value = 100, message = "max [page size] is 100")
  private int size;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return "PageReq [page=" + page + ", size=" + size + "]";
  }

}
