package ThreadPool003;

/**
 * Created by llj on 2019/4/11.
 */
public class RealData implements Data {

    private String result;

    public RealData(String data){
        System.out.println("正在使用data进行网络请求，data："+data+",开始...");
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作执行完毕");
        this.result = "1234567";
    }


    public String getRequest() {
        return result;
    }
}
