package Ex4.Firstquestion;

/**
 * @author 15328
 * 1、编写一个有两个线程的程序，
 *    第一个线程用来计算1~100之间的奇数及个数，
 *    第二个线程用来计算1-100之间的偶数及个数。
 *
 *
 *    一个线程实现
 */
public class NewMain {
    public static final int FROM = 1;
    public static final int TO = 100;
    public static void main(String[] args) {
        ComputeOfNew computeOfNew = new ComputeOfNew(FROM,TO);
        Thread thread = new Thread(computeOfNew);
        thread.start();
    }
}
class ComputeOfNew implements Runnable{
    public static int numOfOdd = 0;
    public static int numOfEven = 0;
    private int from;
    private int to;
    ComputeOfNew(int FROM,int TO){
        this.from = FROM;
        this.to = TO;
    }
    public void compute(int value){
        if(value % 2 != 0) {
            System.out.println("第 " + (++numOfOdd) + " 个奇数为：" + value);
        }
        else{
            System.out.println("第 " + (++numOfEven) + " 个偶数为：" + value);
        }
    }
    @Override
    public void run() {
        for(int i = this.from; i <= this.to; i++){
            compute(i);
        }
    }
}
