package Ex1.Thirdquestion;

import java.util.Scanner;

/**
 * @author 15328
 * *3、输出100~200之间的所有素数。
 */
public class Main {
    public  static int judgeOfPrimeNumber(int n){
        boolean isPrime = true;
        //2为素数
        if(n == 1 || (n % 2 == 0 && n != 2)) {
            //1不是素数、非2的偶数不是素数
            isPrime = false;
            //过滤掉1、4、6、8
        }
        for(int i = 3; i <= Math.sqrt(n); i += 2) {
            //从n为9的时候进入循环 3 5 7 全部素数
            //对9以及9以后的全部奇数做循环
            if(n % i == 0) {
                //非素数奇数
                isPrime = false;
                break;
            }
        }
        if(isPrime){
            return 1;
        }
        else{
            return 0;
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入起始数字和结束数字：");
        int startIndex = input.nextInt();
        int endIndex = input.nextInt();
        System.out.printf("从%d到%d之间的素数为：\n",startIndex,endIndex);
        int index = 0;
        for(int i = startIndex; i <= endIndex; i++) {
            if(judgeOfPrimeNumber(i)==1){
                index++;
                System.out.print(i+" ");
                if(index == 10){
                    System.out.println();
                    index = 0;
                }
            }
        }
    }
}
