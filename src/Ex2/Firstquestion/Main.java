package Ex2.Firstquestion;

import java.util.Scanner;
import Ex1.Firstquestion.Student;

/**
 * @author 15328
 * 1、编写MyDate类，完善上次实验中的人员信息录入，
 * 实现日期合法性判断，包括大小月和闰年。
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name;
        int age, year, month, day;
        double markOfJava;
        int numOfStudent;
        System.out.println("请输入学生数量： ");
        numOfStudent = input.nextInt();
        input.nextLine();
        System.out.printf("请输入这%d个学生的信息：\n", numOfStudent);
        System.out.println("将依次是：名字、年龄、出生年月日、Java成绩");

        String[] names = new String[numOfStudent];
        int[] ages = new int[numOfStudent];
        int[] years = new int[numOfStudent];
        int [] months = new int[numOfStudent];
        int [] days = new int[numOfStudent];
        double [] markOfJavas = new double[numOfStudent];
        int numOfTrue = 0;
        for(int i = 0; i < numOfStudent; i++) {
            name = input.next();
            age = input.nextInt();
            year = input.nextInt();
            month = input.nextInt();
            day = input.nextInt();
            markOfJava = input.nextDouble();
            input.nextLine();
            if(MyDate.judgeOfMonth(year,month,day)){
                names[numOfTrue]=name;
                ages[numOfTrue]=age;
                years[numOfTrue]=year;
                months[numOfTrue]=month;
                days[numOfTrue]=day;
                markOfJavas[numOfTrue]=markOfJava;
                numOfTrue++;
            }
        }
        System.out.println("有效学生信息的人数: " + numOfTrue);
        Student[] students = new Student[numOfTrue];
        for(int j = 0; j < numOfTrue; j++){
            students[j] = new Student(names[j], ages[j], years[j], months[j], days[j], markOfJavas[j]);
        }
        System.out.println("有效学生信息如下：");
        for(Student x:students){
            x.show();
            if(x!=students[students.length-1]){
                System.out.println();
            }
        }
    }
}

