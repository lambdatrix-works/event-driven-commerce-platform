package com.lambdatrix.ecom.common.api.headers;

public record CorrelationContext(String correlationId, String requestId) {

}
