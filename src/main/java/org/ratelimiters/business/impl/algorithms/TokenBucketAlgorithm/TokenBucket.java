package org.ratelimiters.business.impl.algorithms.TokenBucketAlgorithm;

public class TokenBucket {
    private int availableTokens;
    private long lastRefilledTimestamp;

    TokenBucket(int availableTokens){
        this.availableTokens = availableTokens;
    }
    public long getLastRefilledTimestamp() {
        return lastRefilledTimestamp;
    }

    public void setLastRefilledTimestamp(long lastRefilledTimestamp) {
        this.lastRefilledTimestamp = lastRefilledTimestamp;
    }

    public int getAvailableTokens() {
        return availableTokens;
    }

    public void setAvailableTokens(int availableTokens) {
        this.availableTokens = availableTokens;
    }
}
