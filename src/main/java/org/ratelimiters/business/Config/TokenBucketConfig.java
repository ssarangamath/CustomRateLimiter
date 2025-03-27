package org.ratelimiters.business.Config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Builder
public class TokenBucketConfig extends RateLimitConfig{
    private Integer refillCount;
    private TimeUnit timeUnit;
    private Integer maxCapacity;
    private Integer duration;
}
