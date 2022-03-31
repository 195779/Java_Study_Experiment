package Ex2.Thirdquestion;

/**
 * @author 15328
 * *3、设计一个汽车类Vehicle，包含的属性有车轮个数（wheels）和车重（weight）。
 * 小车类Car是Vehicle类的子类，其中包含的属性有载人数（loader）。
 * 卡车类（Truck类）是Car类的子类，其中包含的属性有载重量（payload）。每一个类都有相关数据的输出。
 * 实验要求：（1）汽车类Vehicle的构造方法带有2个参数，分别是wheels和weight。
 * Car类的构造方法带有3个参数，分别为wheels、weight和loader。
 * Truck的构造方法带有4个参数，分别为wheels、weight、loader和payload。
 */
public class Main {
    public static void main(String[] args) {
        Vehicle [] vehicle = {
                new Vehicle(4,600),
                new Car(4,4000,20),
                new Truck(16,9000,3,12000),
                //最后一个元素，用逗号或者不加符号都可以
        };
        for(Vehicle x:vehicle){
            System.out.println(x);
        }
    }
}
class Vehicle{
    protected int whells;
    protected double weight;
    Vehicle(int whells,double weight) {
        this.whells = whells;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "whells: "+whells+" weight: "+String.format("%.2f",weight);
    }
}
class Car extends Vehicle{
    protected int loader;
    Car(int whells,double weight,int loader){
        super(whells,weight);
        this.loader = loader;
    }

    @Override
    public String toString() {
        return "whells: "+whells+" weight: "+String.format("%.2f",weight)+" loader: "+loader;
    }
}
class Truck extends Car{
    protected double playload;
    Truck(int whells,double weight,int loader,double playload){
        super(whells, weight, loader);
        this.playload = playload;
    }

    @Override
    public String toString() {
        return "whells: " + whells + " weight: " + String.format("%.2f", weight) +
                " loader: " + loader + " playload: " + String.format("%.2f",playload);
    }
}
