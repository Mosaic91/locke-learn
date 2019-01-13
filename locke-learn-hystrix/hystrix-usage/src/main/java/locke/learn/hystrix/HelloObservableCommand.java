package locke.learn.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.List;

public class HelloObservableCommand extends HystrixObservableCommand<String> {
    private List<String> names;

    protected HelloObservableCommand(List<String> names) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"));
        this.names = names;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            private String justTest;
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    // process
                    for (String name: names) {
                        // doing
                        subscriber.onNext(name);
                    }

                    subscriber.onCompleted();

                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
