package Ex5.Firstquestion;

import java.io.*;
/**
 * @author 15328
 * 1、编写一个Java应用程序，将已存在的扩展名为.txt的文本文件加密后存入另一个文本文件中。
 * 按模板要求，将【代码1】~【代码7】替换为Java程序代码。
 */

public class SecretExample
{
    public static void main(String a[])
    {
        File fileone=new File("src\\Ex5\\Firstquestion\\hello.txt");
        File filetwo=new File("src\\Ex5\\Firstquestion\\hello.secret");
        //char b[]={'a','b','c','d'};
        char b[]=new char[100];
        try{
            FileReader in =  new FileReader(fileone);
            //创建指向fileone的字符输入流
            FileWriter out = new FileWriter(filetwo);
            //创建指向fileontwo的字符输出流
            int n = -1;
            while((n=in.read(b)) != -1) {
                for(int i = 0; i < n; i++) {
                    b[i] = (char) (b[i] ^ 'a');
                }
                out.write(b, 0, n);
                //out将数组b的前n单元写到文件
            }
            out.close();   //out关闭
            in = new FileReader(filetwo);
            //创建指向fileontwo的字符输入流
            System.out.println("加密后的文件内容：");
            while (n != -1) {
                String str = new String(b, 0, n);
                System.out.println("dfjakfjd" + str);
                n = in.read(b);
            }
            in = new FileReader(filetwo);
            System.out.println("解密后的文件内容：");
            while ( (n=in.read(b)) != -1 ) {
                for (int i = 0; i < n; i++) {
                    b[i] = (char) (b[i] ^ 'a');
                }
                String str = new String(b, 0, n);
                System.out.println(str);
            }
            in.close();   // in关闭
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
