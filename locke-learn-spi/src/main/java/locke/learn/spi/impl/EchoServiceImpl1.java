package locke.learn.spi.impl;

import locke.learn.spi.EchoService;

public class EchoServiceImpl1 implements EchoService {
    public void echo(String msg) {
        System.out.println("1 echo: " + msg);
    }
}
