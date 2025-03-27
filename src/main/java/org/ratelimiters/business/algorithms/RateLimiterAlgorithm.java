package org.ratelimiters.business.algorithms;

import org.ratelimiters.model.ClientRequest;

public interface RateLimiterAlgorithm {
    boolean requestAllowed(ClientRequest clientRequest);
}
