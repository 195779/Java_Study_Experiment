package Ex3.Thirdquestion;
import Ex1.Firstquestion.Student;;import java.io.IOException;
import java.util.Scanner;

/**
 * @author 15328
 * 3、声明一个Average接口，继续完善学生信息录入程序，其中约定求平均值的方法；
 * 声明多个类实现Average接口，分别给出求平均值的方法实现。
 * 例如，在一组数值中，一种算法是，全部数值相加后求平均值，
 * 另一种算法是，去掉一个最高分和一个最低分后，再将总分求平均等；
 * 使用键盘输入数据时，对于不能转换成数值的字符串进行异常处理。
 */
public class Exception3 {
    public static void main(String[] args) {
        System.out.println("请输入学生人数：");
        Scanner input = new Scanner(System.in);
        int numOfStudent = input.nextInt();
        input.nextLine();
        Student []students = new Student[numOfStudent];
        String name;
        String age;
        String year;
        String month;
        String day;
        String marksOfJava;
        for(int i = 0; i < numOfStudent; i++){
            name  = input.next();


            age = input.next();
            year = input.next();
            month = input.next();
            day = input.next();
            marksOfJava = input.next();

            students[i]=new Student(name,throwOfThis(age),throwOfThis(year),throwOfThis(month),
                    throwOfThis(day),throwOfDouble(marksOfJava));
        }
    }
    public static int  throwOfThis(String gripe){
        try{
            Integer.parseInt(gripe);
            return Integer.parseInt(gripe);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return -1;
        }
    }
    public static double throwOfDouble(String markOfJava){
        try{
            Double.parseDouble(markOfJava);
            return Double.parseDouble(markOfJava);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return -1;
        }
    }
}



interface Average{
    void computeOfAverage(Student[] students);
}
class FirstOfCompute implements Average{
    @Override
    public void computeOfAverage(Student[] students) {
        double sum = 0.0;
        for(Student student : students) {
            sum += student.getMarksOfJava();
        }
        System.out.printf("the AverageMarks is: %.2f\n" + sum/students.length);
    }
}
class SecondOfCompute implements Average{
    @Override
    public void computeOfAverage(Student[] students) {
        if(students.length <= 2) {
            System.out.println("数量小于等于2,在去掉最大值和最小值后，无法再计算");
        }
        else {
            double max = students[0].getMarksOfJava();
            double min = students[0].getMarksOfJava();
            double sum = 0.0;
            for(Student student : students){
                if(student.getMarksOfJava() >= max) {
                    max = student.getMarksOfJava();
                }
                else {
                    if(student.getMarksOfJava() <= min) {
                        min = student.getMarksOfJava();
                    }
                }
            }
            for(Student student : students){
                if(student.getMarksOfJava() != max && student.getMarksOfJava() != min) {
                    sum += student.getMarksOfJava();
                }
            }
            System.out.printf("the Average is: %.2f\n",sum/ (students.length-2));
        }
    }
}