package Thread001;

/**
 * 多线程死锁
 * Created by llj on 2019/3/31.
 */
public class ticketSale_deadLock implements Runnable {
    private static int ticketsCount = 100;
    public static Object obj = new Object();
    private boolean flag = true;

    public void run() {
        if (flag == true) {
            while (ticketsCount > 0) {
                synchronized (obj) {
                    try {
                        Thread.sleep(35);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    saleTicket();
                }
            }
        } else {
            while (ticketsCount > 0) {
                saleTicket();
            }
        }
    }

    public synchronized void saleTicket()  {
        synchronized (obj) {
            try {
                Thread.sleep(35);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ticketsCount > 0) {
                System.out.println(Thread.currentThread().getName() + " is selling No." + (100 - ticketsCount + 1) + "ticket");
                ticketsCount--;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ticketSale_deadLock ticketSale = new ticketSale_deadLock();
        Thread thread1 = new Thread(ticketSale, "窗口1");
        Thread thread2 = new Thread(ticketSale, "窗口2");
        thread1.start();
        Thread.sleep(35);
        ticketSale.flag = !ticketSale.flag;
        thread2.start();
    }
}
