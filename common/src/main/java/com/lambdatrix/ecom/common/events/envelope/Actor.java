package com.lambdatrix.ecom.common.events.envelope;

import java.util.List;

public record Actor(ActorType actorType, String actorId, List<String> roles) {

  public Actor {
    roles = roles == null ? List.of() : List.copyOf(roles);
  }
}
