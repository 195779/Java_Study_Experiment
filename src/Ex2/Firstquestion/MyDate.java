package Ex2.Firstquestion;

import java.time.LocalDate;
import java.util.Locale;

public class MyDate{
    public static int isLeapYear(int year){
        if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            return 1;
        }
        else{
            return 0;
        }
    }
    public static boolean judgeOfDay1(int month,int day,boolean isTrue){
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if(day < 1 || day > 31){
                System.out.printf("非法输入日期，%d月没有第%d天\n",month,day);
                isTrue = false;
            }
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11) {
            if(day < 1 || day > 30){
                System.out.printf("非法输入日期，%d月没有第%d天\n",month,day);
                isTrue = false;
            }
        }
        return isTrue;
    }
    public static boolean judgeOfMonth(int year,int month,int day){
        boolean isTrue = true;
        if(month < 0 || month > 12) {
            System.out.printf("非法输入日期，没有第%d个月\n",month);
            isTrue = false;
        }
        else {
            if(month == 2) {
                if(isLeapYear(year) == 1) {
                    /*如果使用LocalDate类，可以直接调用其类中的方法isLeapYear
                     * 来判断是否是闰年，但是也会出现一个问题：
                     * 当输入年份为闰年的时候，如果2月的日期输入错误，
                     * 在构造date对象的时候就会检测到并抛出异常：
                     * Exception in thread "main" java.time.DateTimeException: Invalid date 'FEBRUARY 30'
                     * 	at java.base/java.time.LocalDate.create(LocalDate.java:461)
                     * 	at java.base/java.time.LocalDate.of(LocalDate.java:273)
                     * 	at Ex2.Firstquestion.MyDate.judgeOfMonth(MyDate.java:42)
                     * 	at Ex2.Firstquestion.Main.main(Main.java:39)
                     *
                     *
                     * LocalDate date = LocalDate.of(year, month, day);
                     * if(date.isLeapYear()){
                     **/
                    if(day < 0 || day > 29) {
                        System.out.printf("非法输入日期，闰年%d月没有第%d天\n", month, day);
                        isTrue = false;
                    }
                }
                else {
                    if(day < 0 || day > 28) {
                        System.out.printf("非法输入日期，非闰年%d月没有第%d天\n", month, day);
                        isTrue = false;
                    }
                }
            }
            else{
                isTrue = judgeOfDay1(month,day,isTrue);
            }
        }
        return isTrue;
    }
}