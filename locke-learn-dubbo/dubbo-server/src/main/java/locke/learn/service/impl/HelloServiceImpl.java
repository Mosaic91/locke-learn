package locke.learn.service.impl;

import locke.learn.api.HelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {
    public String sayHello(String msg) {

        System.out.println("get message: " + msg);

        return "hello: " + msg;
    }
}
