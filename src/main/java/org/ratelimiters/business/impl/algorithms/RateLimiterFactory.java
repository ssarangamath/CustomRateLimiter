package org.ratelimiters.business.impl.algorithms;

import org.ratelimiters.business.impl.Config.RateLimitConfig;
import org.ratelimiters.business.impl.algorithms.RollingWindowAlgorithm.RollingWindowRateLimiterAlgorithm;
import org.ratelimiters.business.impl.algorithms.TokenBucketAlgorithm.TokenBucketRateLimiterAlgorithm;
import org.ratelimiters.business.impl.RateLimiterStrategy;

public class RateLimiterFactory {
    private static RateLimiterAlgorithm rateLimiter;

    public static RateLimiterAlgorithm getRateLimiter(RateLimiterStrategy rateLimiterStrategy,
                                                      RateLimitConfig rateLimitConfig){
        switch (rateLimiterStrategy){

            case TOKEN_BUCKET:
                rateLimiter = new TokenBucketRateLimiterAlgorithm(rateLimitConfig);
                break;
            case ROLLING_WINDOW :
                rateLimiter = new RollingWindowRateLimiterAlgorithm(rateLimitConfig);
                break;
            default:
        }
        return rateLimiter;
    }
}
