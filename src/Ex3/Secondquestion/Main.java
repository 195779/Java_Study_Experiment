package Ex3.Secondquestion;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 15328
 * 2、设计一个Java程序，自定义异常类，从命令行（键盘）输入一个字符串，
 * 如果该字符串值为“XYZ”，则抛出一个异常信息“This is a XYZ”，
 * 如果从命令行输入ABC，则没有抛出异常。（只有XYZ和ABC两种输入）。
 */
public class Main {
    public static void main(String[] args) throws customizeException{
        Scanner input = new Scanner(System.in);
        String test = input.next();
        if(test.equals("XYZ")){
            customizeException e = new customizeException("This is a XYZ");
            System.out.println(e);
            throw new customizeException("This is a XYZ");
        }
    }
}
class customizeException extends IOException{
    public customizeException(){}
    public customizeException(String gripe){
        super(gripe);
    }

    @Override
    public String toString() {
        return "This is a XYZ2";
    }

}