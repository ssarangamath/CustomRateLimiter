package org.ratelimiters.business;

public enum RateLimiterStrategy {
    LEAKY_BUCKET, TOKEN_BUCKET, ROLLING_WINDOW, FIXED_WINDOW;
}
