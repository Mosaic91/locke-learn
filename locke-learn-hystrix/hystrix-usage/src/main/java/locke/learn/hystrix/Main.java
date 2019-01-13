package locke.learn.hystrix;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int count = 10;
        while (count > 0) {
            int id = count--;
            new Thread(() -> {
                try {
                    System.out.println(new CommandUsingSemaphoreIsolation(id).execute());
                } catch (Exception ex) {
                    System.out.println("Exception:" + ex.getMessage() + " id=" + id);
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(100);
    }
}
