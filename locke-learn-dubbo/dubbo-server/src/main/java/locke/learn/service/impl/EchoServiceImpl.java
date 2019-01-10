package locke.learn.service.impl;


import locke.learn.api.EchoService;
import org.springframework.stereotype.Component;

@Component
public class EchoServiceImpl implements EchoService {
    public String echo(String msg) {
        return "echo: " + msg;
    }
}
