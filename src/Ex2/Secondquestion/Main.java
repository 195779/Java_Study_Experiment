package Ex2.Secondquestion;

import java.util.Scanner;

/**
 * @author 15328
 * 2、声明一个Person类和派生类Student， * 成员变量包括学号、姓名、入学时间、身份证号、学分绩点等信息，
 * 成员方法包括开户、存款、取款、查询（余额、明细）、销户等操作。
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Person person = new Person("试验者","123456");
        Student student = new Student("Alan_cf","987654321",190001,
                2019,8,21,3.5,60);
        boolean isTrue = true;
        while(isTrue) {
            student.operate();
            System.out.println("***************1、继续选择操作***************");
            System.out.println("***************2、立即退出系统***************");
            int judge = input.nextInt();
            switch(judge){
                case 1:{
                    break;
                }
                case 2:{
                    isTrue = false;
                    break;
                }
                default:
                    System.out.println("非法输入不奏效");
                    break;
            }
        }
    }
}
 class Person{
    Scanner input = new Scanner(System.in);
    protected String name;
    protected String id;
    /**身份证号*/
    protected boolean judgeOfAccount = false;
    /**是否已经开户*/
    protected static final int MAX_ACCOUNTS = 10;
    /**一个人最多10个账户*/
    protected int idOfAccount = 0;
    /**此人的有几个账户*/
    protected Account [] accounts = new Account[MAX_ACCOUNTS];
    /**账户数组*/
    Person(String name,String id){
        this.name = name;
        this.id = id;
    }
    public boolean judgeOfOpenAccount(){
        System.out.println("****1、是****");
        System.out.println("****2、否****");
        int judge = input.nextInt();
        input.nextLine();
        if(judge == 1){
            return true;
        }
        else{
            if(judge == 2){
                return false;
            }
            else {
                System.out.println("没有输入1或2,非法输入,无法创建账户");
                return false;
            }
        }
    }
    public void openOfAccount(){
        //开户
        if(judgeOfAccount){
            System.out.printf("您已经拥有%d个账户,最多可以再开%d个账户\n",idOfAccount,MAX_ACCOUNTS-idOfAccount);
            System.out.println("您确定要创建此账户吗？");
            if(judgeOfOpenAccount()){
                System.out.println("开户成功！");
                accounts[idOfAccount] = new Account(idOfAccount);
                idOfAccount++;
                //账户数量加一
            }
            else{
                System.out.println("取消开户！");
            }
        }
        else{
            System.out.printf("您还没有账户，最多可以开%d个账户\n",MAX_ACCOUNTS);
            System.out.println("您确定要创建此账户吗？");
            if(judgeOfOpenAccount()){
                System.out.println("开户成功！");
                judgeOfAccount = true;
                accounts[idOfAccount] = new Account(idOfAccount);
                idOfAccount++;
                //账户数量加一
            }
            else{
                System.out.println("取消开户！");
            }
        }
    }
    public void depositMoney(int idOfAccount){
        accounts[idOfAccount].depositMoney();
    }
    public void drawMoney(int idOfAccount){
        accounts[idOfAccount].drawMoney();
    }
    public void queryDetails(int idOfAccount){
        if(accounts[idOfAccount]!=null) {
            accounts[idOfAccount].queryDetails();
        }
        else {
            System.out.println("此账户已经销毁");
        }
    }
    public void closeOfAccount(int idOfAccount){
        System.out.println("您确定销除此账户吗");
        if(judgeOfCloseAccount()){
            accounts[idOfAccount]=null;
        }
        else{
            System.out.println("未能销户");
        }
    }
    public boolean judgeOfCloseAccount(){
        System.out.println("****1、是****");
        System.out.println("****2、否****");
        int judge = input.nextInt();
        input.nextLine();
        if(judge == 1){
            return true;
        }
        else{
            if(judge == 2){
                return false;
            }
            else {
                System.out.println("没有输入1或2,非法输入,无法销除账户");
                return false;
            }
        }
    }


    public void operate(){
        if(this instanceof Student){
            System.out.println("学生客户: ");
        }
        else{
            System.out.println("普通客户：");
        }
        System.out.println(this.toString());
        System.out.println("您好，请选择一种操作：");
        System.out.println("**********1、开户**********");
        System.out.println("**********2、存款**********");
        System.out.println("**********3、取款**********");
        System.out.println("**********4、查询**********");
        System.out.println("**********5、销户**********");
        System.out.println("**********6、退出**********");
        int judgeOfOperate = input.nextInt();
        input.nextLine();
        if(judgeOfOperate == 1) {
            openOfAccount();
        }
        else if(judgeOfOperate == 6){
            return;
        }
        else {
            System.out.printf("您一共有%d个账户", idOfAccount);
            System.out.println("请选择其中一个账户：");
            int selectOfAccount = input.nextInt();
            input.nextLine();
            switch (judgeOfOperate) {
                case 2: {
                    depositMoney(selectOfAccount-1);
                    break;
                }
                case 3: {
                    drawMoney(selectOfAccount-1);
                    break;
                }
                case 4: {
                    queryDetails(selectOfAccount-1);
                    break;
                }
                case 5:{
                    closeOfAccount(selectOfAccount-1);
                    idOfAccount--;
                    break;
                }
                default: {
                    System.out.printf("非法输入，其非法值为%d\n", judgeOfOperate);
                    break;
                }
            }
        }
    }
    @Override
    public String toString(){
        return "name: "+this.name+" id: "+id;
    }
}
class Account {
    Scanner input = new Scanner(System.in);
    protected int idOfAccount;
    /**这是此人的第几个账户*/
    protected double money;
    /**金额(默认情况未开户，为0)*/
    protected String [] list = new String[50];
    int listOfList = 0;
    Account(int idOfAccount){
        this.idOfAccount = idOfAccount;
    }
    public void depositMoney() {
        System.out.println("请输入要存入的金额：");
        double deposit = input.nextDouble();
        if(deposit>0){
            money += deposit;
            System.out.printf("已经存入%.2f元，现在总金额为%.2f元\n",deposit,money);
            list[listOfList]="已经存入"+ String.format("%.2f",deposit) +"元"+
                    "现在总金额为"+String.format("%.2f",money)+"元";
            listOfList++;
        }
        else {
            System.out.printf("非法输入金额数值,其非法值为：%.2f\n",deposit);
        }
    }

    public void drawMoney() {
        System.out.println("请输入要取出的金额：");
        double draw = input.nextDouble();
        double money2 = money;
        if(draw>0){
            money2 -= draw;
            if(money2>=0){
                money -= draw;
                System.out.printf("已经取出%.2f元，现在总金额为%.2f元\n",draw,money);
                list[listOfList]="已经取出"+String.format("%.2f",draw)+"元"+
                        "现在总金额为" +String.format("%.2f",money)+"元";
                listOfList++;
            }
            else{
                System.out.printf("现在总金额小于%.2f元，无法取出，现在总金额为%.2f元\n",draw,money);
            }
        }
        else {
            System.out.printf("非法输入金额数值,其非法值为：%.2f\n",draw);
        }
    }

    public void queryDetails() {
        //查询明细
        //现存金额有多少、历次存取详情
        System.out.printf("现在账户内总金额为：%.2f " , money);
        System.out.println("历次存取详情为：");
        for(int i=0;i<listOfList;i++){
            System.out.println(list[i]);
        }
    }

}
class Student extends Person{
    private int stuOfId;
    /**学号*/
    private int startYear;
    private int startMonth;
    private int startDay;
    /**入学时间*/
    private double grades;
    /**绩点*/
    private double credits;
    /*已获得的学分*/

    Student(String name,String id,int stuOfId){
        super(name,id);
        this.stuOfId = stuOfId;
    }
    Student(String name,String id,int stuOfId, double grades){
        this(name,id,stuOfId);
        this.grades = grades;
    }
    Student(String name,String id,int stuOfId,int startYear,int startMonth,int startDay,double grades,double credits){
        this(name,id,stuOfId,grades);
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.credits = credits;
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }
    public void setCredits(double credits){
        this.credits = credits;
    }
    public void setDate(int startYear,int startMonth,int startDay){
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
    }
    @Override
    public String toString(){
        return "name:"+this.name+" id: "+this.id+" stuOfid: "+this.stuOfId;
    }
}
