package Ex1.Secondquestion;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 15328
 * 2、使用Arrays类实现数组排序：使用java.util包中的Arrays类的类方法
 * public static void sort(double a[])
 * 可以把参数a指定的double类型数组按升序排序；
 * public static void sort(double a[], int start , int end)
 * 可以把参数a指定的double类型数组中从位置start到end位置的值按升序排序。
 * 给定数组int a[]={12,34,9,-23,45,6,90,123,19,45,34};
 * 从键盘读入一个整数，使用折半查找判断该整数是否在这个数组中，并将结果输出。
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a[]={12,34,9,-23,45,6,90,123,19,45,34};
        System.out.println("把参数a指定的double类型数组中从位置start到end位置的值按升序排序：");
        System.out.println("请输入startIndex和endIndex：");
        int startIndex = input.nextInt();
        int endIndex = input.nextInt();
        endIndex++;
        //sort：从索引值startIndex到索引值endIndex-1,所以在此对endIndex加一
        input.nextLine();
        Arrays.sort(a,startIndex,endIndex);
        System.out.printf("从%d到%d位置按照升序排序之后的数组为：", startIndex, endIndex - 1);
        System.out.println(Arrays.toString(a));
        System.out.println("把参数a指定的double类型的整个数组按升序排序后: ");
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println("请输入要查找的数字的个数：");
        int numOfSearch = input.nextInt();
        input.nextLine();
        int digitOfSearch;
        int digitJudge;
        while(numOfSearch > 0) {
            System.out.println("请输入要查询的数字：");
            digitOfSearch = input.nextInt();
            digitJudge = Arrays.binarySearch(a,digitOfSearch);
            if(digitJudge >= 0){
                System.out.print("在数组中找到了该数字，其在数组的索引值为：" + digitJudge);
            }
            else {
                System.out.printf("在数组中没有找到该数字，如果将它插入改数组，" +
                        "则应该位于索引值为%d的位置", -(digitJudge + 1));
            }
            if(numOfSearch != 1){
                System.out.println();
            }
            numOfSearch--;
        }
    }
}
