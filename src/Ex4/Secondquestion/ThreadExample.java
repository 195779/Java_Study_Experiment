package Ex4.Secondquestion;

/**
 * @author 15328
 */
class Tortoise extends Thread
{
    int sleepTime = 0;
    int liveLength = 0;
    public Tortoise(String name,int sleepTime, int liveLength)
    {
        this.sleepTime=sleepTime;
        this.liveLength=liveLength;
        this.setName(name);
        // 设置线程的名字为name
    }
    @Override
    public void run()
    {
        while (true ) {
            liveLength--;
            System.out.println("@_@");
            try{
                Thread.sleep(sleepTime);
                // 让线程调用sleep()方法进入中断状态
            }
            catch (InterruptedException e) {

            }
            if (liveLength <= 0 ) {
                System.out.println(getName()+"进入死亡状态\n");
                //?
                break;
                // 结束run()方法的语句
            }
        }
    }
}
class Rabit extends Thread
{
    int sleepTime=0;
    int liveLength=0;
    public Rabit(String name,int sleepTime, int liveLength)
    {
        super(name);// 调用父类构造函数，设置线程的名字为name
        this.sleepTime=sleepTime;
        this.liveLength=liveLength;
    }
    @Override
    public void run()
    {
        while (true)
        {
            liveLength--;
            System.out.println("*_*");
            try{
                sleep( sleepTime);
            }
            catch (InterruptedException e) {}
            if (liveLength<=0)
            {
                System.out.println(getName() + "进入死亡状态\n");
                break;
            }
        }
    }
}
public  class ThreadExample
{
    public static void main(String a[])
    {
        Rabit rabit;
        rabit = new Rabit("rabit",15,55);
        // 新建线程rabit
        Tortoise tortoise = new Tortoise("tortoise",20,50);
        // 新建线程tortoise
        tortoise.start();  // 启动线程tortoise
        rabit.start();// 启动线程rabit
    }
}
