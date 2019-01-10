package locke.learn.spi.impl;

import locke.learn.spi.EchoService;

public class EchoServiceImpl2 implements EchoService {
    public void echo(String msg) {
        System.out.println("2 echo：" + msg);
    }
}
