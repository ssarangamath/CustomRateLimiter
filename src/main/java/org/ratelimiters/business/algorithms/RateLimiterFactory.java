package org.ratelimiters.business.algorithms;

import org.ratelimiters.business.Config.RateLimitConfig;
import org.ratelimiters.business.algorithms.RollingWindowAlgorithm.RollingWindowRateLimiterAlgorithm;
import org.ratelimiters.business.algorithms.TokenBucketAlgorithm.TokenBucketRateLimiterAlgorithm;
import org.ratelimiters.business.RateLimiterStrategy;

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
