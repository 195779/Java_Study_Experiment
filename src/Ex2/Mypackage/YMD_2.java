package Ex2.Mypackage;

/**
 * @author 15328
 */
public class YMD_2 {
    private String name;
    private Test_YMD birth;

    public static void main(String[] args) {
        YMD_2 a = new YMD_2("张弛", 1900, 1, 11);
        a.output();
    }
    public YMD_2(String n1,Test_YMD d1){
        name = n1;
        birth = d1;
    }
    public YMD_2(String n1,int y,int m,int d){
        this(n1,new Test_YMD(y,m,d));
        //初始化变量与对象
    }
    public int age(){
        return Test_YMD.thisyear()- birth.year();
        //返回当前年与出生年的差即年龄
    }
    public void output(){
        System.out.println("姓名：" + name);
        System.out.println("出生日期：" + birth.toString());
        System.out.println("今年年龄：" + age());
    }
}
