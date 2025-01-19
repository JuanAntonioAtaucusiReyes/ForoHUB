package com.challenge.foroHub.persistence.entity;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum Role {
  ADMIN(Arrays.asList(Permissions.values())),
  USER(Arrays.asList(Permissions.values()));

  private final List<Permissions> permissions;

  Role(List<Permissions> permissions) {
    this.permissions = permissions;
  }
}
