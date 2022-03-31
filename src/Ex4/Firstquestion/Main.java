package Ex4.Firstquestion;

/**
 * @author 15328
 * 1、编写一个有两个线程的程序，
 * 第一个线程用来计算1~100之间的偶数及个数，
 * 第二个线程用来计算1-100之间的偶数及个数。
 */
public class Main {
    public static final int DELAY = 100;
    public static void main(String[] args) {
        Compute compute = new Compute(1, 100);
        Runnable task1 = () ->{
            try{
                compute.oddCompute(compute.from,compute.to);
                Thread.sleep((int)(DELAY*Math.random()));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        Runnable task2 = () ->{
            try{
                compute.evenCompute(compute.from,compute.to);
                Thread.sleep((int)(DELAY*Math.random()));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        new Thread(task1).start();
        new Thread(task2).start();
    }
}
class Compute{
    public int from = 1;
    public int to = 100;
    Compute(int from,int to){
        this.from = from;
        this.to = to;
    }
    public void oddCompute(int from,int to){
        //奇数
        int numOfOdd = 0;
        for(int i = from; i <= to; i++){
            if(i % 2 == 1) {
                numOfOdd++;
                System.out.println("第" + numOfOdd + "个奇数为：" + i);
            }
        }
    }
    public void evenCompute(int from,int to){
        //偶数
        int numOfEven = 0;
        for(int i = from; i <= to; i++){
            if(i % 2 == 0) {
                numOfEven++;
                System.out.println("第" + numOfEven + "个偶数为：" + i);
            }
        }
    }
}
