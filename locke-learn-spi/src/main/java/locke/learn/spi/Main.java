package locke.learn.spi;

import com.sun.tools.javac.util.ServiceLoader;

import java.sql.Driver;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<Driver> services = ServiceLoader.load(Driver.class);

        for (Driver driver: services) {

            System.out.println(driver.getClass().getName());
        }

    }
}
