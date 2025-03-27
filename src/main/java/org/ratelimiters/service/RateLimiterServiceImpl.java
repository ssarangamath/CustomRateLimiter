package org.ratelimiters.service;

import org.ratelimiters.business.impl.Config.RateLimitConfig;
import org.ratelimiters.business.impl.RateLimiterStrategy;
import org.ratelimiters.business.impl.algorithms.RateLimiterAlgorithm;
import org.ratelimiters.business.impl.algorithms.RateLimiterFactory;
import org.ratelimiters.model.ClientRequest;

public class RateLimiterServiceImpl implements RateLimiterService{

    private RateLimiterAlgorithm rateLimiterAlgorithm;

    public RateLimiterServiceImpl(RateLimiterStrategy rateLimiterStrategy, RateLimitConfig rateLimitConfig){
        rateLimiterAlgorithm = RateLimiterFactory.getRateLimiter(rateLimiterStrategy, rateLimitConfig);
    }

    @Override
    public boolean isRequestAllowed(ClientRequest clientRequest) {
        return rateLimiterAlgorithm.requestAllowed(clientRequest);
    }
}
