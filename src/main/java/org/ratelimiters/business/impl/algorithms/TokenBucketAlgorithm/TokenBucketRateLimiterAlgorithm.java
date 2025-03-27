package org.ratelimiters.business.impl.algorithms.TokenBucketAlgorithm;

import org.ratelimiters.business.impl.Config.RateLimitConfig;
import org.ratelimiters.business.impl.Config.TokenBucketConfig;
import org.ratelimiters.model.ClientRequest;
import org.ratelimiters.business.impl.algorithms.RateLimiterAlgorithm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketRateLimiterAlgorithm implements RateLimiterAlgorithm {

    private Map<String, TokenBucket> tokenBuckets;
    private final ReentrantLock lock;

    private Integer refillCount;
    private TimeUnit timeUnit;
    private Integer maxCapacity;
    private Integer duration;

    public TokenBucketRateLimiterAlgorithm(RateLimitConfig rateLimitConfig) {
        this.tokenBuckets = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();

        TokenBucketConfig tokenBucketConfig = (TokenBucketConfig) rateLimitConfig;

        refillCount = tokenBucketConfig.getRefillCount();
        timeUnit = tokenBucketConfig.getTimeUnit();
        maxCapacity = tokenBucketConfig.getMaxCapacity();
        duration = tokenBucketConfig.getDuration();
    }

    @Override
    public boolean requestAllowed(ClientRequest clientRequest) {
        String clientId = clientRequest.getClientId();

        TokenBucket tokenBucket = tokenBuckets.computeIfAbsent(clientId, key -> new TokenBucket(maxCapacity));

        refillBucket(tokenBucket);

        if(tokenBucket.getAvailableTokens() > 0){
            tokenBucket.setAvailableTokens(tokenBucket.getAvailableTokens()-1);
            return true;
        }

        return false;
    }

    private void refillBucket(TokenBucket tokenBucket) {
        long elapsedTime = System.currentTimeMillis() - tokenBucket.getLastRefilledTimestamp();

        double tokenFillRatePerMs = this.refillCount / this.timeUnit.toMillis(this.duration);

        int tokensToBeRefilled = (int) (elapsedTime * tokenFillRatePerMs);

        int tokens = Math.min(this.maxCapacity, (tokenBucket.getAvailableTokens()+tokensToBeRefilled));

        tokenBucket.setAvailableTokens(tokens);
        tokenBucket.setLastRefilledTimestamp(System.currentTimeMillis());
    }
}