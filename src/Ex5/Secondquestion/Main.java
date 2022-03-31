package Ex5.Secondquestion;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.nio.file.Files;

/**
 * @author 15328
 * 2、编程完成下列功能：
 * 1）首先建立两个文件myfiel.txt和myfile2.txt。
 * 2）从标准设备中输入多名学生信息，如学号，姓名，专业，班级，家庭住址等，
 * 待输入"bye"时结束，将输入内容保存在myfile1.txt文件中。
 * 3）再将myfile1.txt文件中内容拷贝到myfile2.txt。
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Student [] students = new Student[50];
        String [] pool = new String[250];
        String name;
        String idOfStu;
        String major;
        String classOfStu;
        String address;
        int i = 0;
        int j = 0;
        System.out.println("请输入： ");
        System.out.println("姓名  学号  专业  班级  家庭住址");
        while(true){

            name = input.next();
            if(Objects.equals(name, "bye")){
                break;
            }
            else{
                pool[j++] = name;
            }

            idOfStu = input.next();
            if(Objects.equals(idOfStu, "bye")){
                break;
            }
            else{
                pool[j++] = idOfStu;
            }

            major = input.next();
            if(Objects.equals(major, "bye")){
                break;
            }
            else{
                pool[j++] = major;
            }

            classOfStu = input.next();
            if(Objects.equals(classOfStu, "bye")){
                break;
            }
            else{
                pool[j++] = classOfStu;
            }

            address = input.next();
            if(Objects.equals(address, "bye")){
                break;
            }
            else{
                pool[j++] = address;
            }

            students[i++] = new Student(name,idOfStu,major,classOfStu,address);
        }
        File fileone = new File("src/Ex5/Secondquestion/myfile.txt");
        File filetwo = new File("src/Ex5/Secondquestion/myfile2.txt");
        try {
            //一个个字符写进去
            FileWriter out = new FileWriter(fileone,true);
            //true: 继续在文件末尾加内容
            for(int x = 0; x < j; x++){
                out.write(pool[x],0, pool[x].length());
            }
            out.close();


            FileOutputStream out2 = new FileOutputStream(filetwo,false);
            //filetwo的字节输出流：往filetwo写
            //false ： 覆盖重写原有内容
            Path path  = Paths.get(fileone.getPath());
            //getPath():File类的方法，拿到路径名字符串
            //Paths.get()将路径字符串转换成Path类型的路径对象
            //最终拿到fileone的路径对象path
            Files.copy(path,out2);
            //把文件1所有字节复制到文件2的输出流 覆写filetwo
            out2.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
class Student{
    private String name;
    private String idOfStu;
    private String major;
    private String classOfStu;
    private String address;

    public Student(String name, String idOfStu, String major, String classOfStu, String address) {
        this.name = name;
        this.idOfStu = idOfStu;
        this.major = major;
        this.classOfStu = classOfStu;
        this.address = address;
    }
}

