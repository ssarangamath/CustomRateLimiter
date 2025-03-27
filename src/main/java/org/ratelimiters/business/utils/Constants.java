package org.ratelimiters.business.utils;

public class Constants {
    // rolling window rate limiter constants
    public static final String ROLLING_WINDOW_DURATION = "rolling.window.duration";
    public static final String MAX_REQUESTS_ALLOWED = "max.requests.allowed";

    // token bucket rate limiter constants
    public static final String MAX_CAPACITY = "max.capacity";
    public static final String REFILL_COUNT = "refill.count";
    public static final String REFILL_DURATION = "refill.duration";
    public static final String REFILL_TIMEUNIT = "refill.timeunit";
}
