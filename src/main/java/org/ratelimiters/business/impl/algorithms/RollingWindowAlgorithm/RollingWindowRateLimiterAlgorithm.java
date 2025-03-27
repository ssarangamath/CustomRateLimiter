package org.ratelimiters.business.impl.algorithms.RollingWindowAlgorithm;

import org.ratelimiters.business.impl.Config.RateLimitConfig;
import org.ratelimiters.business.impl.Config.WindowConfig;
import org.ratelimiters.model.ClientRequest;
import org.ratelimiters.business.impl.algorithms.RateLimiterAlgorithm;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class RollingWindowRateLimiterAlgorithm implements RateLimiterAlgorithm {
    private Integer rollingWindowDuration;
    private Integer maxRequestsToBeAllowed;
    private Map<String, Queue<Long>> requestStore;
    ReentrantLock lock;

    public RollingWindowRateLimiterAlgorithm(RateLimitConfig rateLimitConfig){
        requestStore = new ConcurrentHashMap<>();
        lock = new ReentrantLock();

        WindowConfig windowConfig = (WindowConfig)rateLimitConfig;
        rollingWindowDuration = windowConfig.getRollingWindowDuration()*1000;
        maxRequestsToBeAllowed = windowConfig.getMaxRequestsToBeAllowed();
    }


    @Override
    public boolean requestAllowed(ClientRequest clientRequest) {
        Queue<Long> queue;
        String clientId = clientRequest.getClientId();

        if(requestStore.containsKey(clientId)){
            queue = requestStore.get(clientId);
        }else{
            queue = new LinkedList<>();
            requestStore.put(clientId, queue);
        }

        long currentTimestamp = System.currentTimeMillis();

        while (!queue.isEmpty() && queue.peek() < currentTimestamp-rollingWindowDuration){
            queue.poll();
        }

        if(queue.size() < maxRequestsToBeAllowed){
            queue.offer(currentTimestamp);
            return true;
        }

        return false;
    }
}
