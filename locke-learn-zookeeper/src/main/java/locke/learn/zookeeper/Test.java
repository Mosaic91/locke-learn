package locke.learn.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.Charset;


public class Test {
    private static final Charset CHARSET=Charset.forName("UTF-8");

    private static String connectString = "localhost:2181,localhost:2182,localhost:2183";
    private static int sessionTimeout = 5000;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Watcher watcher = new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("监听到的事件：" + event);
            }
        };


        String path = "/zk1/run";


        final ZooKeeper zookeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
        System.out.println("获得连接：" + zookeeper);

//        zookeeper.create()

        zookeeper.create(path, "test".getBytes(CHARSET),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        zookeeper.create(path, "test".getBytes(CHARSET),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        zookeeper.create(path, "test".getBytes(CHARSET),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        final byte[] data = zookeeper.getData(path, watcher, null);
        System.out.println("读取的值：" + new String(data));
        zookeeper.close();

        System.out.println("test");
    }
}
