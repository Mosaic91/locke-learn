package locke.learn.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloWorldCommand extends HystrixCommand<String> {

    private String key;

    protected HelloWorldCommand(String key) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"));
        this.key = key;
    }

    @Override
    protected String run() throws Exception {
        return "hello world: " + key;
    }
}
