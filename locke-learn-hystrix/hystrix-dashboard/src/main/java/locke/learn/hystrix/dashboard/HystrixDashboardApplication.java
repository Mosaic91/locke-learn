package locke.learn.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

//  1.4启动应用，然后再浏览器中输入http://localhost:2001/hystrix可以看到界面
@EnableHystrixDashboard
@SpringCloudApplication
public class HystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }

}