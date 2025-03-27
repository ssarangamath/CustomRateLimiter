package org.ratelimiters.service;

import org.ratelimiters.model.ClientRequest;

public interface RateLimiterService {
    boolean isRequestAllowed(ClientRequest clientRequest);
}
