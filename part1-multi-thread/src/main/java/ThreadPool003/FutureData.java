package ThreadPool003;

/**
 * Created by llj on 2019/4/11.
 */
public class FutureData implements Data{
    private boolean FLAG = false;
    private RealData realData;

    public synchronized void setRealData(RealData realData){
        if (FLAG){
            return;
        }
        this.realData = realData;
        FLAG= true;
        notify();
    }

    public synchronized String getRequest(){
        while(!FLAG){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getRequest();
    }
}
