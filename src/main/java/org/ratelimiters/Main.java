package org.ratelimiters;

import org.ratelimiters.business.impl.Config.RateLimitConfig;
import org.ratelimiters.business.impl.Config.TokenBucketConfig;
import org.ratelimiters.business.impl.Config.WindowConfig;
import org.ratelimiters.model.ClientRequest;
import org.ratelimiters.business.impl.RateLimiterStrategy;
import org.ratelimiters.service.RateLimiterService;
import org.ratelimiters.service.RateLimiterServiceImpl;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        testRollingWindowRateLimit();
          testTokenBucketRateLimit();
    }

    private static void testTokenBucketRateLimit(){
        RateLimitConfig tokenBucketConfig =
                TokenBucketConfig.builder().refillCount(10).duration(1).timeUnit(TimeUnit.SECONDS).maxCapacity(15).build();

        RateLimiterService rateLimiterService = new RateLimiterServiceImpl(RateLimiterStrategy.TOKEN_BUCKET, tokenBucketConfig);

        ClientRequest clientRequest = new ClientRequest("client-id-1", "client-1");
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1 Req Allowed :: "+
                        rateLimiterService.isRequestAllowed(clientRequest));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2 Req Allowed :: "+
                        rateLimiterService.isRequestAllowed(clientRequest));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        while (true){
            Thread thread1 = new Thread(runnable1);
            thread1.run();

            Thread thread2 = new Thread(runnable2);
            thread2.run();
        }
    }

    private static void testRollingWindowRateLimit(){
        RateLimitConfig rollingWindowConfig = WindowConfig.builder().rollingWindowDuration(2).maxRequestsToBeAllowed(10).build();

        RateLimiterService rateLimiterService = new RateLimiterServiceImpl(RateLimiterStrategy.ROLLING_WINDOW, rollingWindowConfig);

        ClientRequest clientRequest = new ClientRequest("client-id-1", "client-1");
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1 Req Allowed :: "+
                        rateLimiterService.isRequestAllowed(clientRequest));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2 Req Allowed :: "+
                        rateLimiterService.isRequestAllowed(clientRequest));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        while (true){
            Thread thread1 = new Thread(runnable1);
            thread1.run();

            Thread thread2 = new Thread(runnable2);
            thread2.run();
        }
    }
}