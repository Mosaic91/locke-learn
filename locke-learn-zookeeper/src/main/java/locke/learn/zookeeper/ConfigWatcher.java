package locke.learn.zookeeper;


import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

public class ConfigWatcher implements Watcher{
    private ActiveKeyValueStore store;

    @Override
    public void process(WatchedEvent event) {
        System.out.println("ConfigWatcher get Event:" + event);
//        if(event.getType()==EventType.NodeDataChanged){
//        EventType.NodeCreated, EventType.NodeDeleted, EventType.NodeChildrenChanged, EventType.NodeDataChanged, EventType.None
        try{
            dispalyConfig();
        }catch(InterruptedException e){
            System.err.println("Interrupted. exiting. ");
            Thread.currentThread().interrupt();
        }catch(KeeperException e){
            System.out.printf("KeeperException锛?s. Exiting.\n", e);
        }

    }
    public ConfigWatcher() throws IOException {
        store=new ActiveKeyValueStore();
        store.connect();
    }
    public void dispalyConfig() throws KeeperException, InterruptedException{
        String value=store.read(ConfigUpdater.PATH, this);
        System.out.printf("Read %s as %s\n",ConfigUpdater.PATH,value);
    }

    public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
        ConfigWatcher configWatcher = new ConfigWatcher();
        configWatcher.dispalyConfig();
        //stay alive until process is killed or Thread is interrupted
        Thread.sleep(Long.MAX_VALUE);
    }
}