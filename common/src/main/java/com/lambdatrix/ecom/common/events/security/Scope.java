package com.lambdatrix.ecom.common.events.security;

public enum Scope {
  CATALOG_READ("catalog.read"),
  CATALOG_WRITE("catalog.write"),
  ORDER_READ("order.read"),
  ORDER_WRITE("order.write");

  private final String value;

  Scope(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

  public static Scope fromValue(String value) {
    for (Scope s : values()) {
      if (s.value.equals(value)) return s;
    }
    throw new IllegalArgumentException("Unknown scope: " + value);
  }
}
