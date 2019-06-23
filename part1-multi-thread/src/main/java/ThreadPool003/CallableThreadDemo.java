package ThreadPool003;

import java.util.concurrent.*;

/**
 * Created by llj on 2019/4/11.
 */

class CallableATask implements Callable{

    public Object call() throws Exception {
        System.out.println("子线程正在被调用。。需耗时5s");
        Thread.sleep(5000);
        return "success";
    }
}

public class CallableThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new CallableATask());
        System.out.println("主线程调用子线程。。。");
        String o = future.get();
        System.out.println("获取执行结果 result:"+o);
        executorService.shutdown();
        System.out.println("线程池已关闭");
    }
}
