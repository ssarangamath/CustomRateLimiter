package org.ratelimiters.business.impl;

public enum RateLimiterStrategy {
    LEAKY_BUCKET, TOKEN_BUCKET, ROLLING_WINDOW, FIXED_WINDOW;
}
