package Redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

public class RedisKeyJava {
    public static void main(String[] args) {
        //连接本地的Redis服务
        Jedis jedis=new Jedis("localhost");
        System.out.println("连接成功");

        //获取数据并输出
        Set<String> keys=jedis.keys("*");
        Iterator<String> it=keys.iterator();
        while(it.hasNext()){
            String key=it.next();
            System.out.println(key);
        }
    }
}
