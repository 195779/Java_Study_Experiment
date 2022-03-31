package Ex1.Firstquestion;

public class Student{
    private String name;
    private int age;
    private double marksOfJava;
    private int year;
    private int month;
    private int day;

    public Student(String name,int age,int year,int month,int day,double marksOfJava){
        this.name = name;
        this.age = age;
        this.year = year;
        this.month = month;
        this.day = day;
        this.marksOfJava = marksOfJava;
    }
    public void show(){
        System.out.printf("name: %15s  age: %3d  year: %5d  month: %3d  day: %3d  markOfJava: %6.2f",
                this.name,this.age, this.year,this.month,this.day,this.marksOfJava);
    }

    public double getMarksOfJava() {
        return this.marksOfJava;
    }
}