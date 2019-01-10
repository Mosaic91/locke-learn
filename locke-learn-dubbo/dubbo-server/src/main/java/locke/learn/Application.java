package locke.learn;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author liuweibo
 * @date 2018/5/9
 */
public class Application {
    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( new String[] { "dubbo.xml" });
        context.start();
        System.out.println("任意按键退出");
        System.in.read();
        context.close();
    }
}
