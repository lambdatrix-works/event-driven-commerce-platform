package com.lambdatrix.ecom.common.events.envelope;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

import com.lambdatrix.ecom.common.events.types.DomainEventType;

public record EventEnvelope<T>(
    String eventId,
    DomainEventType eventType,
    String aggregateType,
    String aggregateId,
    Instant timestamp,
    Integer version,
    String correlationId,
    String causationId,
    String tenantId,
    Actor actor,
    T payload,
    Map<String, String> metadata) {

  public EventEnvelope {
    if (timestamp == null) timestamp = Instant.now();
    Objects.requireNonNull(eventType, "eventType can not be null");
    Objects.requireNonNull(aggregateType, "aggregateType can not be null");
    Objects.requireNonNull(aggregateId, "aggregateId can not be null");
    Objects.requireNonNull(version, "version can not be null");
    Objects.requireNonNull(correlationId, "correlationId can not be null");
    Objects.requireNonNull(tenantId, "tenantId can not be null");
    Objects.requireNonNull(actor, "actor can not be null");
    Objects.requireNonNull(payload, "payload can not be null");

    metadata = metadata == null ? Map.of() : Map.copyOf(metadata);
  }

  public static <T> Builder<T> builder() {
    return new Builder<>();
  }

  public static final class Builder<T> {

    private String eventId;
    private DomainEventType eventType;
    private String aggregateType;
    private String aggregateId;
    private Instant timestamp = Instant.now();
    private Integer version;
    private String correlationId;
    private String causationId;
    private String tenantId;
    private Actor actor;
    private T payload;
    private Map<String, String> metadata;

    public Builder<T> eventId(String eventId) {
      this.eventId = eventId;
      return this;
    }

    public Builder<T> eventType(DomainEventType eventType) {
      this.eventType = eventType;
      return this;
    }

    public Builder<T> aggregateType(String aggregateType) {
      this.aggregateType = aggregateType;
      return this;
    }

    public Builder<T> aggregateId(String aggregateId) {
      this.aggregateId = aggregateId;
      return this;
    }

    public Builder<T> version(Integer version) {
      this.version = version;
      return this;
    }

    public Builder<T> correlationId(String correlationId) {
      this.correlationId = correlationId;
      return this;
    }

    public Builder<T> causationId(String causationId) {
      this.causationId = causationId;
      return this;
    }

    public Builder<T> tenantId(String tenantId) {
      this.tenantId = tenantId;
      return this;
    }

    public Builder<T> actor(Actor actor) {
      this.actor = actor;
      return this;
    }

    public Builder<T> payload(T payload) {
      this.payload = payload;
      return this;
    }

    public Builder<T> metadata(Map<String, String> metadata) {
      this.metadata = metadata;
      return this;
    }

    public EventEnvelope<T> build() {
      return new EventEnvelope<T>(
          eventId,
          eventType,
          aggregateType,
          aggregateId,
          timestamp,
          version,
          correlationId,
          causationId,
          tenantId,
          actor,
          payload,
          metadata);
    }
  }
}
