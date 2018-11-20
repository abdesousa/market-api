package com.market.api.auth;

import java.util.Objects;

import com.market.api.model.User;

import io.dropwizard.auth.Authorizer;

/**
 * 
 * @author Alex Sousa (abdesousa@gmail.com)
 *
 */
public class MarketApiAuthorizer implements Authorizer<User> {
  public boolean authorize(User principal, String role) {
    // Allow any logged in user.
    if (Objects.nonNull(principal)) {
      return true;
    }
    return false;
  }
}