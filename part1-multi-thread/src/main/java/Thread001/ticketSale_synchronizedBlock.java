package Thread001;

/**
 * Created by llj on 2019/3/31.
 */
public class ticketSale_synchronizedBlock implements Runnable {
    private int ticketsCount = 100;

    public void run() {

        while (ticketsCount > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            saleTicket();
        }
    }

    public void saleTicket() {
        synchronized (this) {
            if (ticketsCount > 0) {
                System.out.println(Thread.currentThread().getName() + " is selling No." + (100 - ticketsCount + 1) + "ticket");
                ticketsCount--;
            }
        }
    }

    public static void main(String[] args) {
        ticketSale_synchronizedBlock ticketSale = new ticketSale_synchronizedBlock();
        Thread thread1 = new Thread(ticketSale);
        Thread thread2 = new Thread(ticketSale);
        thread1.start();
        thread2.start();
    }
}
