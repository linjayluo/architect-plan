package JVM;

/**
 * Created by llj on 2019/6/9.
 */
public class TestJVM01 {
    public static void main(String[] args) {
        byte[] bytes = new byte[4 * 1024 * 1024];
        System.out.println("分配了4M空间给数组");

        System.out.println("最大（堆）内存：" );
        System.out.println( Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");

        System.out.println("可用内存：" );
        System.out.println( Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");


        System.out.println("已使用内存：" );
        System.out.println( Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");


    }
}
