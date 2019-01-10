package locke.learn.zookeeper;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.KeeperException;

public class ConfigUpdater {


    public static final String  PATH="/config";

    private ActiveKeyValueStore store;
    private Random random=new Random();

    public ConfigUpdater() throws IOException, InterruptedException {
        store = new ActiveKeyValueStore();
        store.connect();
    }
    public void run() throws InterruptedException, KeeperException{
        while(true){
            String value=random.nextInt(100)+"";
            store.write(PATH, value);
            System.out.println("Set "+PATH+" to "+value+"\n");
            TimeUnit.SECONDS.sleep(1);
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ConfigUpdater configUpdater = new ConfigUpdater();
        configUpdater.run();
    }
}