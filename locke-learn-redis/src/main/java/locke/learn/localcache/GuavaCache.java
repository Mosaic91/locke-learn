package locke.learn.localcache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class GuavaCache {

    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(500)
                .initialCapacity(10)
                .build();
    }
}
