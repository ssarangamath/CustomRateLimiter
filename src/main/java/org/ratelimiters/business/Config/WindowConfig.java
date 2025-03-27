package org.ratelimiters.business.Config;

import lombok.Builder;

@Builder
public class WindowConfig extends RateLimitConfig{
    private Integer rollingWindowDuration;
    private Integer maxRequestsToBeAllowed;

    public Integer getRollingWindowDuration() {
        return rollingWindowDuration;
    }

    public Integer getMaxRequestsToBeAllowed() {
        return maxRequestsToBeAllowed;
    }
}
