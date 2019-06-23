package JVM;


import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出
 * VM Options：-Xms:1m -Xmx:10m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * Created by llj on 2019/6/9.
 */
public class TestJVM03 {

    public static void main(String[] args){
        List<Object> list = new ArrayList<>();

        for (int i=0;i<10;i++){
            byte[] bytes = new byte[1024 * 1024];
            list.add(bytes);
        }

        System.out.println("finished!");
    }

}