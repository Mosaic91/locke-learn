package locke.learn.localcache;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {
    private static RateLimiter rateLimiter = RateLimiter.create(10);


    public static void main(String[] args) {
        for (int i = 0 ; i < 1000; i++) {
            new Thread(() -> {
                if (rateLimiter.tryAcquire()) {
                    System.out.println("db： " + System.currentTimeMillis());
                } else {
                    System.err.println("fail:");
                }
            }).start();
        }
    }
}
