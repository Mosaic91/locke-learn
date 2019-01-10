package locke.learn.zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ConnectionWatcher {
    private static String connectString = "localhost:2181,localhost:2182,localhost:2183";
    private static int sessionTimeout = 5000;

    protected ZooKeeper zk;

    public void connect() throws IOException {

        zk = new ZooKeeper(connectString, sessionTimeout, event -> System.out.println("监听到的事件：" + event));

    }

}
