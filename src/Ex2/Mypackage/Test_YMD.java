package Ex2.Mypackage;

import java.util.Calendar;

/**
 * @author 15328
 */
public class Test_YMD {
    private int year,month,day;
    public static void main(String[] args) {

    }
    public Test_YMD(int y,int m,int d){
        year = y;
        month = (((m >= 1) & (m <= 12)) ? m : 1);
        day = (((d >= 1) & (d <= 31)) ? d : 1);
    }
    public Test_YMD(){
        this(0,0,0);
    }
    public static int thisyear(){
        return Calendar.getInstance().get(Calendar.YEAR);
        //返回当年的年份
    }
    public int year(){
        return year;
        //返回年份
    }
    @Override
    public String toString(){
        return year + "-" + month + "-" + day;
    }
}
