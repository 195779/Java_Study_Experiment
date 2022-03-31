package Ex4.Thirdquestion;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 15328
 * *3、编写Java应用程序模拟5个人排队买票。
 * 售票员只有1张五元的钱，电影票五元钱一张。
 * 假设5个人的名字及排队顺序是：赵、钱、孙、李、周。
 * “赵”拿1张二十元的人民币买2张票，
 * “钱”拿1张二十元的人民币买1张票，
 * “孙”1张十元的人民币买1张票，
 * “李”拿1张十元的人民币买2张票，
 * “周”拿1张五元的人民币买1张票。
 * 要求售票员按如下规则找赎：
 * （1）二十元买1张票，找零：1张十元；不许找零2张五元。
 * （2）二十元买1张票，找零：1张十元，1张五元；不许找零3张五元。
 * （3）十元买一张票，找零1张五元。
 */
public class Buy extends Thread {

    Money money;

    private  int fiveOfNumber;
    private  int tenOfNumber;
    private  int twentyOfNumber;
    private int ticketOfNum;
    private String name;
    public Buy(Money money,int fiveOfNumber, int tenOfNumber, int twentyOfNumber,int ticketOfNum,String name) {
        this.fiveOfNumber = fiveOfNumber;
        this.tenOfNumber = tenOfNumber;
        this.twentyOfNumber = twentyOfNumber;
        this.ticketOfNum = ticketOfNum;
        this.money = money;
        this.name = name;
        Thread.currentThread().setName(this.name);
    }


    @Override
    public void run() {
        Thread.currentThread().setName(this.name);
        money.givechange(fiveOfNumber,tenOfNumber,twentyOfNumber,ticketOfNum,Thread.currentThread().getName());
    }
}




class Money {
    //收银员的钱的数量
    private static int fiveOfNum = 1;
    private static int tenOfNum = 0;
    private static int twentyOfNum = 0;

    public Lock lock;
    public Condition condition;
    public Money(boolean fair){
        lock = new ReentrantLock(fair);
        //公平锁
        condition = lock.newCondition();
    }


    public void givechange1(int fiveOfNumber, int tenOfNumber, int twentyOfNumber, int ticketOfNumber, String name) {
        System.out.print("客户原有" + fiveOfNumber + "张5元," + ",买票总钱数是:" + (ticketOfNumber * 5));
        fiveOfNumber -= ticketOfNumber;
        fiveOfNum += ticketOfNumber;
        System.out.println(", 客户现有" + fiveOfNumber + "张5元" + ", 客户现有" + tenOfNumber + "张10元"
                + ", 客户现有" + twentyOfNumber + "张20元" + " 不用找零，现在售货员剩余五元：" + fiveOfNum + "张"
                + " 、十元： " + tenOfNum + "张" + " 、 二十元： " + twentyOfNum + "张");
        condition.signalAll();
}

    public void givechange2(int fiveOfNumber, int tenOfNumber, int twentyOfNumber, int ticketOfNumber, String name) throws InterruptedException {
        if(ticketOfNumber % 2 == 0) {
            System.out.print("客户原有" + tenOfNumber + "张10元," + ",买票总钱数是:" + (ticketOfNumber * 5));
            tenOfNumber -= ticketOfNumber / 2;
            tenOfNum += ticketOfNumber / 2;
            System.out.println(", 客户现有" + fiveOfNumber + "张5元" + ", 客户现有" + tenOfNumber + "张10元"
                    + ", 客户现有" + twentyOfNumber + "张20元" + " 不用找零，现在售货员剩余五元：" + fiveOfNum + "张"
                    + " 、十元： " + tenOfNum + "张" + " 、 二十元： " + twentyOfNum + "张");
        } else {
            //买奇数张票，有一张需要找零
            if(fiveOfNum <= 0) {
                System.out.println("要找零一张五元钱，但是没有，线程睡眠");
                condition.await();
                System.out.print(name + " ");
            }
            if(!(fiveOfNum <= 0)) {
                System.out.print("客户原有" + tenOfNumber + "张10元," + ",买票总钱数是: " + (ticketOfNumber * 5));
                fiveOfNum--;
                fiveOfNumber++;
                tenOfNum += (ticketOfNumber + 1) / 2;
                tenOfNumber -= (ticketOfNumber + 1) / 2;
                System.out.println(", 客户现有" + fiveOfNumber + "张5元" + ", 客户现有" + tenOfNumber + "张10元"
                        + ", 客户现有" + twentyOfNumber + "张20元" + " 找零5元，现在售货员剩余五元：" + fiveOfNum + "张"
                        + " 、十元： " + tenOfNum + "张" + " 、 二十元： " + twentyOfNum + "张");
            }
        }
    }
    public void givechange3(int fiveOfNumber, int tenOfNumber, int twentyOfNumber, int ticketOfNumber, String name) throws InterruptedException {
        if(ticketOfNumber % 2 == 0) {
            if(ticketOfNumber >= 4 && ticketOfNumber % 4 == 0) {
                //4的倍数
                System.out.print("客户原有" + twentyOfNumber + "张20元," + ",买票总钱数是: " + (ticketOfNumber * 5));
                twentyOfNumber -= ticketOfNumber / 4;
                twentyOfNum += ticketOfNumber / 4;
                System.out.println(", 客户现有" + fiveOfNumber + "张5元" + ", 客户现有" + tenOfNumber + "张10元"
                        + ", 客户现有" + twentyOfNumber + "张20元" + " 不用找零，现在售货员剩余五元：" + fiveOfNum + "张"
                        + " 、十元： " + tenOfNum + "张" + " 、 二十元： " + twentyOfNum + "张");
                condition.signalAll();
            } else {
                //只是2的倍数，不是4的倍数，找一张10元零钱
                //2、6、10、14、
                System.out.print("客户原有" + twentyOfNumber + "张20元," + ",买票总钱数是: " + (ticketOfNumber * 5));
                if(tenOfNum <= 0) {
                    System.out.println("要找零一张十元钱，但是没有，线程睡眠");
                    condition.await();
                    System.out.print(name + " ");
                }
                if(!(tenOfNum <= 0)) {
                    System.out.print("客户原有" + twentyOfNumber + "张20元," + ",买票总钱数是: " + (ticketOfNumber * 5));
                    tenOfNum--;
                    tenOfNumber++;
                    twentyOfNum += (ticketOfNumber + 2) / 4;
                    twentyOfNumber -= (ticketOfNumber + 2) / 4;
                    System.out.println(", 客户现有" + fiveOfNumber + "张5元" + ", 客户现有" + tenOfNumber + "张10元" + ", 客户现有" + twentyOfNumber + "张20元" + " ,要找零10元，现在售货员剩余五元：" + fiveOfNum + "张"
                            + " 、十元： " + tenOfNum + "张" + " 、 二十元： " + twentyOfNum + "张");
                    condition.signalAll();
                }
            }
        } else {
            //不是2的倍数的时候
            if((ticketOfNumber - 1) % 4 == 0) {
                //1、5、9、、、、、
                if(tenOfNum <= 0 || fiveOfNum <= 0) {
                    System.out.print("客户原有" + twentyOfNumber + "张20元," + ",买票总钱数是: " + (ticketOfNumber * 5));
                    System.out.println("要找零一张五元钱和一张十元钱，但是没有，线程睡眠");
                    condition.await();
                    System.out.print(name + " ");
                }
                if(!(tenOfNum <= 0 || fiveOfNum <= 0)) {
                    System.out.print("客户原有" + twentyOfNumber + "张20元," + ",买票总钱数是: " + (ticketOfNumber * 5));
                    fiveOfNum--;
                    fiveOfNumber++;
                    tenOfNum--;
                    tenOfNumber++;
                    twentyOfNumber -= (ticketOfNumber / 4 + 1);
                    twentyOfNum += (ticketOfNumber / 4 + 1);
                    System.out.println(", 客户现有" + fiveOfNumber + "张5元" + ", 客户现有" + tenOfNumber + "张10元"
                            + ", 客户现有" + twentyOfNumber + "张20元" + " ,要找零一张10元和一张五元，现在售货员剩余五元：" + fiveOfNum + "张"
                            + " 、十元： " + tenOfNum + "张" + " 、 二十元： " + twentyOfNum + "张");
                    condition.signalAll();
                }
            } else if((ticketOfNumber + 1) % 4 == 0) {
                //3、7、11、、、、、
                if(fiveOfNum <= 0) {
                    System.out.println("要找零一张五元钱，但是没有，线程睡眠");
                    condition.await();
                    System.out.print(name + " ");
                }
                if(!(fiveOfNum <= 0)) {
                    System.out.print("客户原有" + twentyOfNumber + "张20元," + ",买票总钱数是: " + (ticketOfNumber * 5));
                    fiveOfNum--;
                    fiveOfNumber++;
                    twentyOfNumber -= (ticketOfNumber / 4 + 1);
                    twentyOfNum += (ticketOfNumber / 4 + 1);
                    System.out.println(", 客户现有" + fiveOfNumber + "张5元" + ", 客户现有" + tenOfNumber + "张10元"
                            + ", 客户现有" + twentyOfNumber + "张20元" + " ,要找零一张五元，现在售货员剩余五元：" + fiveOfNum + "张"
                            + " 、十元： " + tenOfNum + "张" + " 、 二十元： " + twentyOfNum + "张");
                    condition.signalAll();
                }
            }
        }
    }


    public void givechange(int fiveOfNumber,int tenOfNumber,int twentyOfNumber,int ticketOfNumber,String name)  {
        try {
            lock.lock();
            System.out.print(name + " ");
            if(fiveOfNumber != 0 && tenOfNumber == 0 && twentyOfNumber == 0) {
                if(ticketOfNumber > fiveOfNumber) {
                    System.out.println("钱不够买票");
                    return;
                } else {
                    //只带了五元
                    givechange1(fiveOfNumber, tenOfNumber, twentyOfNumber, ticketOfNumber, name);
                }
            } else if(fiveOfNumber == 0 && tenOfNumber != 0 && twentyOfNumber == 0) {
                if(ticketOfNumber * 5 > tenOfNumber * 10) {
                    System.out.println("钱不够买票");
                    return;
                } else {
                    //只带了十元
                    givechange2(fiveOfNumber, tenOfNumber, twentyOfNumber, ticketOfNumber, name);
                }
            } else if(fiveOfNumber == 0 && tenOfNumber == 0 && twentyOfNumber != 0) {
                if(ticketOfNumber * 5 > twentyOfNumber * 20) {
                    System.out.println("钱不够买票");
                    return;
                } else {
                    //只带了20元
                    givechange3(fiveOfNumber, tenOfNumber, twentyOfNumber, ticketOfNumber, name);
                }
            } else {
                System.out.println("非法输入");
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class TicketSeller{
    public static void main(String[] args) throws InterruptedException {
        Money money = new Money(true);
        //多个Buy类型线程处理一个money对象，即多人排队在一个售票处买票
        /*new Buy(money, 0, 4, 0,3,"1号").start();
        Thread.sleep(100);
        new Buy(money, 0, 0, 1,3,"2号").start();
        Thread.sleep(100);
        new Buy(money, 0, 0, 1,1,"3号").start();
        Thread.sleep(100);
        new Buy(money, 0, 1, 0,2,"4号").start();
        Thread.sleep(100);
        new Buy(money, 0, 3, 0,4,"5号").start();
        Thread.sleep(100);
        new Buy(money, 3, 0, 0,3,"6号").start();
        Thread.sleep(100);
        new Buy(money, 3, 0, 0,4,"7号").start();
        Thread.sleep(100);
        new Buy(money, 0, 0, 2,4,"8号").start();
        Thread.sleep(100);
        new Buy(money, 0, 0, 2,8,"9号").start();
        Thread.sleep(100);
        new Buy(money, 0, 0, 2,7,"10号").start();
        Thread.sleep(100);*/
        //System.out.println(Thread.currentThread().getName()+"99999");
        Buy [] buys = new Buy[5];
        buys [0] = new Buy(money, 0, 0, 1,2,"赵");
        buys[0].setName("赵");
        buys [1] = new Buy(money, 0, 0, 1,1,"钱");
        buys[1].setName("钱");
        //System.out.println(Thread.currentThread().getName()+"5555555");
        buys [2] = new Buy(money, 0, 1, 0,1,"孙");
        buys[2].setName("孙");
        buys [3] = new Buy(money, 0, 1, 0,2,"李");
        buys[3].setName("李");
        buys [4] = new Buy(money, 1, 0, 0,1,"周");
        buys[4].setName("周");
        //System.out.println(Thread.currentThread().getName()+"888888");
        for(int i = 0; i < 5; i++) {
            buys[i].start();
            //System.out.println(i);
            Thread.sleep(1);
            /*for(int j = 0; j < 5; j++) {
                if(buys[j].isAlive()) {
                    System.out.println("****    " + buys[j].getName()+" is alive" + "    ****");
                }
                else{
                    System.out.println("****    "+buys[j].getName()+" is destroy" + "    ****");
                }
            }
            Thread.sleep(150);*/
        }
    }
}

