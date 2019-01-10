package locke.learn.zookeeper.joingroup;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.zookeeper.KeeperException;

public class ListGroup extends ConnectionWatcher {

    private static final String HOST2="localhost:2181,localhost:2182,localhost:2183";

    public void list(String groupNmae) throws KeeperException, InterruptedException{
        String path ="/"+groupNmae;
        try {
            List children = zk.getChildren(path, false);
            if(children.isEmpty()){
                System.out.println("Group "+groupNmae+" does not exist \n");
                System.exit(1);
            }
            Iterator it=children.iterator();
            while(it.hasNext()){
                String child=(String)it.next();
                System.err.println(child);
            }
        } catch (KeeperException.NoNodeException e) {
            System.out.println("Group "+groupNmae+" does not exist \n");
            System.exit(1);
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ListGroup listGroup = new ListGroup();
        listGroup.connect(HOST2);
        listGroup.list("");
        listGroup.close();
    }
}