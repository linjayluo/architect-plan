package JVM;

/**
 *
 * -Xss1m 设置最大调用深度
 * Created by llj on 2019/6/9.
 */
public class TestJVM04 {

    private static int count;
    private static void count(){
        try {
            count++;
            count();
        }catch (Throwable e){
            System.out.println("最大深度："+count);
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        count();
    }
}
