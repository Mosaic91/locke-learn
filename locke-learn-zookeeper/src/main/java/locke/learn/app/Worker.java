package locke.learn.app;

import java.util.concurrent.TimeUnit;

public class Worker {


    @DaemonCrane("crane-job-test")
    public void execute () {
        while (true) {
            System.out.println("working,time: " + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
