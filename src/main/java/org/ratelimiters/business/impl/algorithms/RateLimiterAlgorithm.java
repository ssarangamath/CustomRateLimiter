package org.ratelimiters.business.impl.algorithms;

import org.ratelimiters.model.ClientRequest;

public interface RateLimiterAlgorithm {
    boolean requestAllowed(ClientRequest clientRequest);
}
