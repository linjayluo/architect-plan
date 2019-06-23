package Thread001;

/**
 * Created by llj on 2019/3/31.
 */
public class ticketSale_synchronizedMethod_thisLock implements Runnable {
    private static int ticketsCount = 100;
    public boolean flag = false;
    private static Object obj = new Object();

    public void run() {
        if (flag == true) {
            while (ticketsCount > 0) {
                synchronized (obj) {
                    try {
                        Thread.sleep(24);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (ticketsCount > 0) {
                        System.out.println(Thread.currentThread().getName() + " is selling No." + (100 - ticketsCount + 1) + "ticket");
                        ticketsCount--;
                    }
                }
            }
        } else {
            while (ticketsCount > 0) {
                saleTicket();
            }
        }
    }

    public synchronized void saleTicket() {
        try {
            Thread.sleep(24);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (ticketsCount > 0) {
            System.out.println(Thread.currentThread().getName() + " is selling No." + (100 - ticketsCount + 1) + "ticket");
            ticketsCount--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ticketSale_synchronizedMethod_thisLock ticketSale = new ticketSale_synchronizedMethod_thisLock();
        Thread thread1 = new Thread(ticketSale, "窗口1");
        Thread thread2 = new Thread(ticketSale, "窗口2");
        Thread thread3 = new Thread(ticketSale, "窗口3");
        Thread thread4 = new Thread(ticketSale, "窗口4");
        thread1.start();
        Thread.sleep(35);
        ticketSale.flag = !ticketSale.flag;
        thread2.start();
        Thread.sleep(35);
        ticketSale.flag = !ticketSale.flag;
        thread3.start();
        Thread.sleep(35);
        ticketSale.flag = !ticketSale.flag;
        thread4.start();
        Thread.sleep(35);
    }
}
