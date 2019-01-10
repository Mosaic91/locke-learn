package locke.learn.dubbo.service;

import locke.learn.api.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EchoFacade {

    @Autowired
    private HelloService helloService;

    public String sayHello(String hello) {
        System.out.println("invoking facade");
        return helloService.sayHello(hello);
    }
}
