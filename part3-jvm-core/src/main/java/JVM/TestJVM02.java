package JVM;

/**
 * Created by llj on 2019/6/9.
 */
public class TestJVM02 {
    public static void main(String[] args) {

//        -Xms20m -Xmx20m
//        -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:NewRatio=2
        byte[] bytes = null;

        for (int i = 0; i < 10; i++) {
            bytes = new byte[4 * 1024 * 1024];
        }


    }
}
