package com.lambdatrix.ecom.common.util;

import java.util.UUID;

public interface IDs {

  public static String uuid() {
    return UUID.randomUUID().toString();
  }

  public static String requestId() {
    return "req:" + uuid().replace("-", "");
  }
}
