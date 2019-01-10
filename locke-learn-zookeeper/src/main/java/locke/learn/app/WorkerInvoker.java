package locke.learn.app;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class WorkerInvoker {

    private Worker worker;


    private String path = "/Lock";
    private ZooKeeper zookeeper;

    private volatile boolean gotLock = false;


    public WorkerInvoker(Worker worker) {
        this.worker = worker;
    }

    public void init() {
        Watcher watcher = event -> System.out.println("监听到的事件：" + event);

        try {
            String connectString = "localhost:2181,localhost:2182,localhost:2183";
            int sessionTimeout = 1000;
            zookeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
            if (zookeeper.exists(path, false) == null) {
                zookeeper.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    private int getWorkerIdx(String s) {
        String[] split = s.split("#");
        return Integer.parseInt(split[1]);
    }

    private void tryStartWork(int myWorkIdx, Method method) throws KeeperException, InterruptedException, InvocationTargetException, IllegalAccessException {
        List<String> children = zookeeper.getChildren(path, (event) -> {
            if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                System.out.println("子节点变化");

                if (!gotLock) {
                    System.out.println("再次尝试获取锁");

                    try {
                        tryStartWork(myWorkIdx, method);
                    } catch (KeeperException | IllegalAccessException | InterruptedException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.out.println("getChildren:" + children);

        for (String c: children) {
            int workerIdx = getWorkerIdx(c);
            if (workerIdx < myWorkIdx) {
                return;
            }
        }

        System.out.println("get Lock");
        gotLock = true;

        method.invoke(worker);
    }

    public void workerStop() {
        try {
//            zookeeper.delete(myPath, 0);
            zookeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void workerStart() {
        init();

        try {

            Method[] methods = worker.getClass().getMethods();
            for (Method method: methods) {
                DaemonCrane annotation = method.getAnnotation(DaemonCrane.class);

                if (annotation == null ){
                    continue;
                }
                String value = annotation.value();

//                String tmpPath = initWithWorkPath(value);


                String myPath = zookeeper.create(path + "/" +value + "-worker#", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

                int myWorkIdx = getWorkerIdx(myPath);

                tryStartWork(myWorkIdx, method);
            }

        } catch (InterruptedException | KeeperException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        WorkerInvoker workerInvoker = new WorkerInvoker(new Worker());
        workerInvoker.workerStart();
        Thread.sleep(Long.MAX_VALUE);
        workerInvoker.workerStop();
    }
}
