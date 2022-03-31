package Algorithms.A_First;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static int gcd(int p,int q){
        /*最大公约数算法（欧几里得算法）：
        * 计算两个非负整数p和q的最大公约数：若q=0,则最大公约数为p
        * 若q!=0，将p除以q得到余数r，p和q的最大公约数即q和r的最大公约数
        * **/
        if(q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q,r);
    }
    public static void greatestCommonDivisor(int p, int q){
        System.out.println("最大公约数为：" + gcd(p,q));
    }
    public static int bS(double key, ArrayList<Double> number){
        /*二分查找：前提：数组必须是有序的
         * */
        int index = -1;
        int high = number.size() - 1;
        int low = 0;
        int mid = 0;
        while(low <= high){
            mid = (low + high) / 2;
            if(number.get(mid)< key){
                low = mid + 1;
            }
            else
            if(number.get(mid) == key){
                index = mid;
                break;
            }
            else
            if(number.get(mid) > key){
                high = mid - 1;
            }
        }
        return index;
    }
    public static void binarySearch(double key, ArrayList<Double> number){
        if(bS(key,number) == -1){
            System.out.println("未能找到");
        }
        else{
            System.out.println("已经找到，其索引位置为：" + bS(key,number));
        }
    }
    public static void operate(){
        Scanner input = new Scanner(System.in);
        boolean symbol = true;
        while(symbol){
            System.out.println("*****1、计算最大公约数*****");
            System.out.println("*****2、在有序数组中二分查找某个数字*****");
            System.out.println("*****0、退出测试*****");
            int Judge = input.nextInt();
            switch (Judge){
                case 0:{
                    symbol = false;
                    break;
                }
                case 1: {
                    System.out.println("请输入p和q: ");
                    int p = input.nextInt();
                    int q = input.nextInt();
                    greatestCommonDivisor(p,q);
                    break;
                }
                case 2: {
                    ArrayList<Double> num = new ArrayList<>();
                    System.out.println("请输入该数组元素：");
                    /*输入过程*/
                    input.nextLine();
                    String number = input.nextLine();
                    Scanner input2 = new Scanner(number);
                    while(input2.hasNextDouble()){
                        num.add(input2.nextDouble());
                    }

                    num.sort(Comparator.naturalOrder());
                    System.out.println("排序后的数组：");
                    System.out.println(num.toString());
                    System.out.println("请输入要查找的元素：");
                    double key = input.nextDouble();
                    binarySearch(key,num);
                    break;
                }
                default:
                    System.out.println("错误输入");
            }
        }
    }
    public static void main(String[] args) {
        operate();
    }
}
