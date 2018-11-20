package com.market.api.auth;

import java.util.Optional;

import com.market.api.model.User;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

/**
 * 
 * @author Alex Sousa (abdesousa@gmail.com)
 *
 */
public class MarketApiAuthenticator implements Authenticator<String, User> {
  
  public Optional<User> authenticate(String token) throws AuthenticationException {
    if ("test_token".equals(token)) {
      return Optional.of(new User());
    }
    return Optional.empty();
  }
}