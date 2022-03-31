package Ex3.Firstquestion;

/**
 * @author 15328
 * 1、用try-catch-finally结构实现异常处理。编译并运行程序，写出程序运行结果。
 */
public class Main {
    public static void main(String[] args) {
        int i = 0;
        String greeting [] = {"Hello","Only","Test"};
        while(i<4){
            try{
                System.out.println(greeting[i]);
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("数组越界");
            }
            finally {
                System.out.println("总会运行");
            }
            i++;
        }
    }
}
