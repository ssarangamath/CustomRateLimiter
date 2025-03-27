package org.ratelimiters.business.impl.Config;

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
