package Ex2.forthquestion;

import java.util.Scanner;

/**
 * @author 15328
 * 4、定义接口Shape及其抽象方法getArea()和getPerimeter()用于计算图形和面积和周长。
 * 定义类Rectangle(矩形)、类Circle(圆形)、类Triangle(三角形)，
 * 要求这些类继承点类Coordinates()并实现接口的抽象方法。
 */
public class Main {
    public static void showOfSelect(){
        System.out.println("选择一种图形形状：");
        System.out.println("****1、矩形****");
        System.out.println("****2、圆形****");
        System.out.println("****3、三角形****");
    }
    public static boolean judgeOfData(long x){
        if(x < 0) {
            System.out.printf("非法输入,其首个非法值为：%4d\n",x);
            return false;
        }
        else{
            return true;
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long x,y,r,z;
        System.out.println("请输入要计算的图形的数量：");
        int num = input.nextInt();
        Coordinate [] coordinates = new Coordinate[num];
        int judgeOfShape = 0;
        for(int i = 0; i < num; i++){
            showOfSelect();
            judgeOfShape = input.nextInt();
            input.nextLine();
            switch(judgeOfShape){
                case 1:{
                    System.out.println("请输入长和宽：");
                    x = input.nextLong();
                    y = input.nextLong();
                    input.nextLine();
                    if(!judgeOfData(x) || !judgeOfData(y)) {
                        // 如果第一个为FALSE，第二个则不再执行，所以如果两个输入都非法，
                        // 那么只会显示第一个非法值的数值
                        break;
                    }
                    coordinates[i] = new Rectangle(x, y);
                    System.out.printf("矩形面积：%4.2f\n",coordinates[i].getArea());
                    System.out.printf("矩形周长：%4.2f\n",coordinates[i].getPerimeter());
                    break;
                }
                case 2: {
                    System.out.println("请输入圆心x坐标、y坐标、半径r: ");
                    x = input.nextLong();
                    y = input.nextLong();
                    r = input.nextLong();
                    input.nextLine();
                    if(!judgeOfData(r)) {
                        break;
                    }
                    coordinates[i] = new Circle(x,y,r);
                    System.out.printf("圆形面积：%4.2f\n",coordinates[i].getArea());
                    System.out.printf("圆形周长：%4.2f\n",coordinates[i].getPerimeter());
                    break;
                }
                case 3: {
                    System.out.println("请输入边长x、边长y、边长z: ");
                    x = input.nextLong();
                    y = input.nextLong();
                    z = input.nextLong();
                    input.nextLine();
                    if(!judgeOfData(x) || !judgeOfData(y) || !judgeOfData(z)) {
                        // 如果第一个为FALSE，第二个则不再执行，所以如果两个输入都非法，
                        // 那么只会显示第一个非法值的数值
                        break;
                    }
                    if(x + y > z && x - y < z && y - x < z && x + z > y && x - z < y
                            && z - x < y && y + z > x && y - z < x && z - y < x) {
                        coordinates[i] = new Triangle(x,y,z);
                        System.out.printf("三角形面积：%4.2f\n",coordinates[i].getArea());
                        System.out.printf("三角形周长：%4.2f\n",coordinates[i].getPerimeter());
                        break;
                    }
                    else{
                        System.out.printf("边%d、 %d、 %d、不能组成三角形\n",x,y,z);
                        break;
                    }
                }
                default: {
                    System.out.printf("%d为非法输入\n",judgeOfShape);
                }
            }
        }
    }
}
class Coordinate implements Shape {
    long x;
    long y;
    Coordinate(long x, long y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }
}

interface Shape{
    double getArea();//面积
    double getPerimeter();//周长
}

class Rectangle extends Coordinate implements Shape{
    /**矩形类用xy做长和宽*/
    Rectangle(long x,long y){
        super(x,y);
    }
    @Override
    public double getArea(){
        return (double)(this.x*this.y);
    }

    @Override
    public double getPerimeter() {
        return (double)(2*(this.x+this.y));
    }
}

class Circle extends Coordinate implements Shape{
    /**圆形类，xy表示圆心坐标，另外设radius表示半径长度*/
    private long radius;
    Circle(long x,long y,long radius){
        super(x,y);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return  Math.PI * (double)this.radius * (double)this.radius;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * 2.0 * (double)this.radius;
    }
}
class Triangle extends Coordinate implements Shape{
    /**三角形类，xyz三条边长度*/
    private long z;
    Triangle(long x,long y,long z){
        super(x,y);
        this.z = z;
    }

    @Override
    public double getArea() {
        //海伦公式
        double p =(double)(this.x+this.y+this.z)/2.0;
        return Math.sqrt(p * (p - (double) this.x) * (p - (double) this.y) * (p - (double) this.z));
    }

    @Override
    public double getPerimeter() {
        return (double)(this.x+this.y+this.z);
    }
}