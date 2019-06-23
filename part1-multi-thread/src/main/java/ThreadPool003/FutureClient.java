package ThreadPool003;

/**
 * Created by llj on 2019/4/11.
 */
public class FutureClient  {

   public Data submit(String requestData){
       final FutureData futureData  = new FutureData();
       new Thread(new Runnable() {
           public void run() {
               RealData realData = new RealData("3554365");
               futureData.setRealData(realData);
           }
       }).start();

       return futureData;
   }

}
