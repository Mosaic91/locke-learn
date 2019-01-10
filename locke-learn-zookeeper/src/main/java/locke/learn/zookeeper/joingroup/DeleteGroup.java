package locke.learn.zookeeper.joingroup;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.zookeeper.KeeperException;

public class DeleteGroup extends ConnectionWatcher{

    private static final String HOST1="localhost:2181";

    public void delete(String groupName) throws InterruptedException, KeeperException{
        String path="/"+groupName;
        List children;
        try {
            children = zk.getChildren(path, false);
            Iterator it=children.iterator();
            while(it.hasNext()){
                zk.delete(path+"/"+(String)it.next(), -1);
            }

            zk.delete(path, -1);
        } catch (KeeperException.NoNodeException e) {
            System.out.println("Group "+groupName+" does not exist \n");
            System.exit(1);
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect(HOST1);
        deleteGroup.delete("a");
        deleteGroup.close();
    }
}
