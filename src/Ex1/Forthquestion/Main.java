package Ex1.Forthquestion;

/**
 * @author 15328
 * *4、采用for循环求1至1000之内的所有“完全数”。
 * 所谓“完全数”是指一个数，恰好等于它的因子之和。
 * 例如，6是一个完全数，因为6的因子为1、2、3，而6＝1+2+3。
 * 补充自百度百科：
 * 完全数（Perfect number），又称完美数或完备数，是一些特殊的自然数。
 * 它所有的真因子（即除了自身以外的约数）的和（即因子函数），恰好等于它本身。
 */
public class Main {
    public static void main(String[] args) {
       int sum = 0;
        int i = 2;
        int j = 1;
        for( i = 2; i <= 1000; i++) {
            for( j = 1; j < i; j++){
                if(i % j == 0) {
                    sum += j;
                }
            }
            if(sum == i) {
                System.out.print(sum + "  ");
            }
            sum = 0;
        }
    }
}
