package Thread001;

/**
 * Created by llj on 2019/4/2.
 */

class Res {
    public ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public Integer getNumber() {
        int count = threadLocal.get() + 1;
        threadLocal.set(count);
        return count;
    }
}

public class ThreadLocalDemo extends Thread {
    private Res res;

    ThreadLocalDemo(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "," + res.getNumber());
        }
    }

    public static void main(String[] args) {
        Res res = new Res();
        ThreadLocalDemo thread1 = new ThreadLocalDemo(res);
        ThreadLocalDemo thread2 = new ThreadLocalDemo(res);
        thread1.start();
        thread2.start();
    }
}
