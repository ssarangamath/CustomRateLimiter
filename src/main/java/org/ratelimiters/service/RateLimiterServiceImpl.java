package org.ratelimiters.service;

import org.ratelimiters.business.Config.RateLimitConfig;
import org.ratelimiters.business.RateLimiterStrategy;
import org.ratelimiters.business.algorithms.RateLimiterAlgorithm;
import org.ratelimiters.business.algorithms.RateLimiterFactory;
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
