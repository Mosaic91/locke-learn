package locke.learn.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class RedisPipeLine {

    public static void testPipeLineAndNormal(Jedis jedis)
            throws InterruptedException {
        Logger logger = Logger.getLogger("javasoft");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            jedis.set(String.valueOf(i), String.valueOf(i));
        }
        long end = System.currentTimeMillis();
        logger.info("the jedis total time is:" + (end - start));

        Pipeline pipe = jedis.pipelined(); // 先创建一个pipeline的链接对象
        long start_pipe = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            pipe.set(String.valueOf(i), String.valueOf(i));
        }
        pipe.sync(); // 获取所有的response
        long end_pipe = System.currentTimeMillis();
        logger.info("the pipe total time is:" + (end_pipe - start_pipe));

        BlockingQueue<String> logQueue = new LinkedBlockingQueue<String>();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            logQueue.put("i=" + i);
        }
        long stop = System.currentTimeMillis();
        logger.info("the BlockingQueue total time is:" + (stop - begin));
    }

}
