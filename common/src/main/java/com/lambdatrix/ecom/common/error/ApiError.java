package com.lambdatrix.ecom.common.error;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.lambdatrix.ecom.common.util.IDs;

public record ApiError(
    String errorId,
    Instant timestamp,
    String status,
    ErrorCode errorCode,
    String message,
    String path,
    String correlationId,
    Map<String, String> details) {

  public ApiError {
    if (errorId == null) errorId = IDs.uuid();
    if (timestamp == null) timestamp = Instant.now();
    Objects.requireNonNull(status, "status must not be null");
    Objects.requireNonNull(errorCode, "errorCode must not be null");
    Objects.requireNonNull(message, "message must not be null");
    Objects.requireNonNull(path, "path must not be null");

    details = (details == null) ? Map.of() : Map.copyOf(details);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String errorId = IDs.uuid();
    private Instant timestamp = Instant.now();
    private String status;
    private ErrorCode errorCode;
    private String message;
    private String path;
    private String correlationId;
    private Map<String, String> details = new LinkedHashMap<>();

    public Builder status(String status) {
      this.status = status;
      return this;
    }

    public Builder errorCode(ErrorCode errorCode) {
      this.errorCode = errorCode;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder path(String path) {
      this.path = path;
      return this;
    }

    public Builder correlationId(String correlationId) {
      this.correlationId = correlationId;
      return this;
    }

    public Builder addDetail(String k, String v) {
      this.details.put(k, v);
      return this;
    }

    public ApiError build() {
      return new ApiError(
          errorId, timestamp, status, errorCode, message, path, correlationId, details);
    }
  }
}
