package Ex1.Firstquestion;

import java.util.Scanner;

/**
 * @author 15328
 * 1、编写一个Java应用程序，用户从键盘输入十名学生的信息，
 * 至少包括姓名、年龄、出生年月日、java课程实验成绩，
 * 成绩使用浮点数，年龄使用整型，程序将输出年龄、java课程实验成绩的平均值。
 * 提示：Scanner对象调用nextDouble()或nextFloat()可以获取用户从键盘输入的浮点数。
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name;
        int age,year,month,day;
        double markOfJava;
        int numOfStudent;
        System.out.println("请输入学生数量： ");
        numOfStudent = input.nextInt();
        input.nextLine();
        System.out.printf("请输入这%d个学生的信息：\n",numOfStudent);
        double sumOfMarks = 0;
        double sumOfAge = 0;
        Student [] students = new Student[numOfStudent];
        for(int i = 0; i < numOfStudent; i++ ){
            name = input.next();
            age  = input.nextInt();
            year = input.nextInt();
            month = input.nextInt();
            day = input.nextInt();
            markOfJava = input.nextDouble();
            sumOfAge += (double)age;
            sumOfMarks += markOfJava;
            input.nextLine();
            students[i] = new Student(name,age,year,month,day,markOfJava);
            /* 或者不用前面一系列变量给构造函数赋值，直接将输入数值用作构造函数参数使用
             * students[i] = new Student(input.next(),input.nextInt(),input.nextInt(),
             * input.nextInt(),input.nextInt(),input.nextDouble());*/
        }
        for(Student x: students){
            x.show();
            System.out.println();
        }
        System.out.println("平均年龄为："+String.format("%.2f",sumOfAge/numOfStudent));
        System.out.print("平均Java成绩为："+String.format("%.2f",sumOfMarks/numOfStudent));
    }
}
