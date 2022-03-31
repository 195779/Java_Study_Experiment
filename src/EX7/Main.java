package EX7;

import java.util.Scanner;

/**
 * @author 15328
 */
public class Main {

    protected String string_sql;
    protected String forname;
    protected String url;
    protected String user;
    protected String password;

    protected static final String string_sql_x = "select * from student_x";
    protected static final String forname_x = "com.mysql.cj.jdbc.Driver";
    protected static final String url_x = "jdbc:mysql://localhost:3306/test_db";
    protected static final String user_x = "root";
    protected static final String password_x = "123456";

    public Main(String string_sql, String forname, String url, String user, String password) {
        this.string_sql = string_sql;
        this.forname = forname;
        this.url = url;
        this.user = user;
        this.password = password;
    }


    public static void main(String[] args) throws Exception {
        /**查询对象，用于查询全表*/
        StatementExcute statementExcute = new StatementExcute(
                "select * from student_x", forname_x,url_x,user_x,password_x);

        PreparedStatementExecuteInsert preparedStatementExecuteInsert = new PreparedStatementExecuteInsert(
                "insert into student_x values(?,?,?);",forname_x, url_x,user_x,password_x);

        PreparedStatementExecuteInsert preparedStatementExecuteInsert1 = new PreparedStatementExecuteInsert(
                "insert into student_x values(?,?,?,?);",forname_x, url_x,user_x,password_x);

        StatementExcuteUpdate statementExcuteUpdate = new StatementExcuteUpdate(
                "", forname_x,url_x,user_x,password_x);

        PreparedStatementExecuteDelete preparedStatementExecuteDelete = new PreparedStatementExecuteDelete(
                "delete from student_x where name=?;", forname_x,url_x,user_x,password_x);


        PreparedStatementExcuteUpdate preparedStatementExcuteUpdate1 = new PreparedStatementExcuteUpdate(
                "update student_x set name=? where name=?",forname_x, url_x,user_x,password_x);
        PreparedStatementExcuteUpdate preparedStatementExcuteUpdate2 = new PreparedStatementExcuteUpdate(
                "update student_x set sex=? where name=?",forname_x, url_x,user_x,password_x);
        PreparedStatementExcuteUpdate preparedStatementExcuteUpdate3 = new PreparedStatementExcuteUpdate(
                "update student_x set age=? where name=?",forname_x, url_x,user_x,password_x);
        PreparedStatementExcuteUpdate preparedStatementExcuteUpdate4 = new PreparedStatementExcuteUpdate(
                "update student_x set score=? where name=?",forname_x, url_x,user_x,password_x);


        PreparedStatmentExecute preparedStatmentExecute1 = new PreparedStatmentExecute(
                "select * from student_x where name=?;",forname_x, url_x,user_x,password_x);
        PreparedStatmentExecute preparedStatmentExecute2 = new PreparedStatmentExecute(
                "select * from student_x where sex=?;",forname_x, url_x,user_x,password_x);
        PreparedStatmentExecute preparedStatmentExecute3 = new PreparedStatmentExecute(
                "select * from student_x where age=?;",forname_x, url_x,user_x,password_x);
        PreparedStatmentExecute preparedStatmentExecute4 = new PreparedStatmentExecute(
                "select * from student_x where score=?;",forname_x, url_x,user_x,password_x);

        operate(statementExcute,
                preparedStatementExecuteInsert,
                preparedStatementExecuteInsert1,
                statementExcuteUpdate,
                preparedStatementExecuteDelete,
                preparedStatementExcuteUpdate1,
                preparedStatementExcuteUpdate2,
                preparedStatementExcuteUpdate3,
                preparedStatementExcuteUpdate4,
                preparedStatmentExecute1,
                preparedStatmentExecute2,
                preparedStatmentExecute3,
                preparedStatmentExecute4);
    }
    public static void show_all_table(StatementExcute statementExcute) throws Exception {
        statementExcute.statementexcute("");
    }
    public static void show_one_table(PreparedStatmentExecute preparedStatmentExecute1,
                                      PreparedStatmentExecute preparedStatmentExecute2,
                                      PreparedStatmentExecute preparedStatmentExecute3,
                                      PreparedStatmentExecute preparedStatmentExecute4,
                                      StatementExcute statementExcute) throws Exception {
        System.out.println("当前字段有：");
        int count = statementExcute.show_all_classname();
        Scanner input = new Scanner(System.in);
        System.out.println("*****1、按姓名查询*****");
        System.out.println("*****2、按性别查询*****");
        System.out.println("*****3、按年龄查询*****");
        if(count==4)
        {
            System.out.println("*****4、按成绩查询*****");
        }
        int judge_select = input.nextInt();
        input.nextLine();
        switch (judge_select){
            case 1:{
                System.out.println("请输入姓名：");
                String name = input.next();
                input.nextLine();
                preparedStatmentExecute1.preparedstatementexecute1(name);
                break;
            }
            case 2:{
                System.out.println("请输入性别：");
                String sex = input.next();
                input.nextLine();
                preparedStatmentExecute2.preparedstatementexecute1(sex);
                break;
            }
            case 3:{
                System.out.println("请输入年龄：");
                int age = input.nextInt();
                input.nextLine();
                preparedStatmentExecute3.preparedstatementexecute2(age);
                break;
            }
            case 4:{
                if(count!=4){
                    System.out.println("非法输入");
                    break;
                }
                System.out.println("请输入成绩：");
                int score = input.nextInt();
                input.nextLine();
                preparedStatmentExecute4.preparedstatementexecute2(score);
                break;
            }
            default:{
                System.out.println("非法输入");
                break;
            }
        }
    }
    public static void show_table(StatementExcute statementExcute,
                                  PreparedStatmentExecute preparedStatmentExecute1,
                                  PreparedStatmentExecute preparedStatmentExecute2,
                                  PreparedStatmentExecute preparedStatmentExecute3,
                                  PreparedStatmentExecute preparedStatmentExecute4) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("*****1、查询全表数据*****");
        System.out.println("*****2、单个参数查询*****");
        System.out.println("*****3、输入语句查询*****");
        int judge_select = input.nextInt();
        input.nextLine();
        switch(judge_select){
            case 1: {
                show_all_table(statementExcute);
                break;
            }
            case 2:{
                show_one_table(preparedStatmentExecute1,preparedStatmentExecute2,
                        preparedStatmentExecute3,preparedStatmentExecute4,statementExcute);
                break;
            }
            case 3:{
                show_one_table_inSQL(statementExcute);
                break;
            }
            default:{
                System.out.println("非法输入");
                break;
            }
        }
    }

    public static void show_one_table_inSQL(StatementExcute statementExcute) throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("请输入SQL语句:");
        String sql = input.nextLine();
        statementExcute.string_sql = "";
        statementExcute.statementexcute(sql);
        statementExcute.string_sql = string_sql_x;
    }

    public static void insert_one(PreparedStatementExecuteInsert preparedStatementExecuteInsert
    ,PreparedStatementExecuteInsert preparedStatementExecuteInsert1,StatementExcute statementExcute) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("当前字段有：");
        int count = statementExcute.show_all_classname();
        System.out.println("请输入要插入数据的内容");
        if(count==3)
        {
            System.out.println("***Name***Sex***Age***");
            String name = input.next();
            String sex = input.next();
            int age = input.nextInt();
            input.nextLine();
            preparedStatementExecuteInsert.preparedstatementexecuteinsert(name,sex,age);
        }
        if(count==4)
        {
            System.out.println("***Name***Sex***Age***Score");
            String name = input.next();
            String sex = input.next();
            int age = input.nextInt();
            int score =input.nextInt();
            input.nextLine();
            preparedStatementExecuteInsert1.preparedstatementexecuteinsert1(name,sex,age,score);
        }
    }

    public static void do_one_in_SQL(StatementExcuteUpdate statementExcuteUpdate,StatementExcute statementExcute) throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("当前字段有：");
        statementExcute.show_all_classname();
        System.out.println("请输入SQL语句：");
        String sql = input.nextLine();
        statementExcuteUpdate.statementexcuteupdate(sql);
    }

    public static void update_one(PreparedStatementExcuteUpdate preparedStatementExcuteUpdate1,
                                  PreparedStatementExcuteUpdate preparedStatementExcuteUpdate2,
                                  PreparedStatementExcuteUpdate preparedStatementExcuteUpdate3,
                                  PreparedStatementExcuteUpdate preparedStatementExcuteUpdate4,
                                  StatementExcute statementExcute) throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("当前字段有：");
        int count = statementExcute.show_all_classname();
        System.out.println("*****1、只改名字*****");
        System.out.println("*****2、只改性别*****");
        System.out.println("*****3、只改年龄*****");
        if(count==4)
        {
            System.out.println("*****4、只改成绩*****");
        }
        int judge_u = input.nextInt();
        input.nextLine();
        switch (judge_u){
            case 1:{
                System.out.println("*****name_new*****name_old*****");
                System.out.println("输入参数：");
                String name_new = input.next();
                String name_old = input.next();
                input.nextLine();
                preparedStatementExcuteUpdate1.preparedstatementexecuteupdate(name_new,name_old);
                break;
            }
            case 2:{
                System.out.println("*****sex_new*****name*****");
                System.out.println("输入参数：");
                String sex_new = input.next();
                String name_s = input.next();
                input.nextLine();
                preparedStatementExcuteUpdate2.preparedstatementexecuteupdate(sex_new,name_s);
                break;
            }
            case 3:{
                System.out.println("*****age_new*****name*****");
                System.out.println("输入参数：");
                int age_new = input.nextInt();
                String name_a = input.next();
                input.nextLine();
                preparedStatementExcuteUpdate3.prepreparedstatementexecuteupdate2(age_new,name_a);
                break;
            }
            case 4:{
                if(count==3){
                    System.out.println("非法输入");
                    break;
                }
                System.out.println("*****score_new*****name*****");
                System.out.println("输入参数：");
                int score_new = input.nextInt();
                String name_a = input.next();
                input.nextLine();
                preparedStatementExcuteUpdate4.prepreparedstatementexecuteupdate2(score_new,name_a);
                break;
            }
            default:{
                System.out.println("非法输入");
                break;
            }
        }
    }

    public static void delete_one(PreparedStatementExecuteDelete preparedStatementExecuteDelete) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("输入姓名，删除该行");
        String name = input.next();
        input.nextLine();
        preparedStatementExecuteDelete.preparedstatementexecutedelete(name);
    }



    public static void operate(StatementExcute statementExcute,
                               PreparedStatementExecuteInsert preparedStatementExecuteInsert,
                               PreparedStatementExecuteInsert preparedStatementExecuteInsert1,
                               StatementExcuteUpdate statementExcuteUpdate,
                               PreparedStatementExecuteDelete preparedStatementExecuteDelete,
                               PreparedStatementExcuteUpdate preparedStatementExcuteUpdate1,
                               PreparedStatementExcuteUpdate preparedStatementExcuteUpdate2,
                               PreparedStatementExcuteUpdate preparedStatementExcuteUpdate3,
                               PreparedStatementExcuteUpdate preparedStatementExcuteUpdate4,
                               PreparedStatmentExecute preparedStatmentExecute1,
                               PreparedStatmentExecute preparedStatmentExecute2,
                               PreparedStatmentExecute preparedStatmentExecute3,
                               PreparedStatmentExecute preparedStatmentExecute4) throws Exception {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("****************选项****************");
            System.out.println("********1、数据查询********");
            System.out.println("********2、数据插入********");
            System.out.println("********3、数据修改********");
            System.out.println("********4、数据删除********");
            System.out.println("********5、修改字段********");
            System.out.println("********6、成绩统计********");
            System.out.println("********7、退出系统********");
            int x = input.nextInt();
            input.nextLine();
            if(x==7){
                break;
            }
            switch(x){
                case 1:{
                    show_table(statementExcute,
                            preparedStatmentExecute1,
                            preparedStatmentExecute2,
                            preparedStatmentExecute3,
                            preparedStatmentExecute4);
                    break;
                }
                case 2:{
                    System.out.println("*****1、输入具体SQL语句进行数据插入*****");
                    System.out.println("*****2、输入字段参数进行数据插入   *****");
                    int judge = input.nextInt();
                    if(judge == 2){
                        insert_one(preparedStatementExecuteInsert,preparedStatementExecuteInsert1,statementExcute);
                    }
                    else{
                        do_one_in_SQL(statementExcuteUpdate,statementExcute);
                    }
                    System.out.println("插入新记录完成，现在该表的数据如下：");
                    show_all_table(statementExcute);
                    break;
                }
                case 3:{
                    System.out.println("*****1、输入具体SQL语句进行数据修改*****");
                    System.out.println("*****2、输入字段参数进行数据修改   *****");
                    int judge = input.nextInt();
                    if(judge == 2){
                        update_one(preparedStatementExcuteUpdate1,
                                preparedStatementExcuteUpdate2,
                                preparedStatementExcuteUpdate3,
                                preparedStatementExcuteUpdate4,
                                statementExcute);
                    }
                    else{
                        do_one_in_SQL(statementExcuteUpdate,statementExcute);
                    }
                    System.out.println("修改记录完成，现在该表的数据如下：");
                    show_all_table(statementExcute);
                    break;
                }
                case 4:{
                    System.out.println("*****1、输入具体SQL语句进行数据删除*****");
                    System.out.println("*****2、输入字段参数进行数据删除   *****");
                    int judge = input.nextInt();
                    if(judge == 2){
                        delete_one(preparedStatementExecuteDelete);
                    }
                    else{
                        do_one_in_SQL(statementExcuteUpdate,statementExcute);
                    }
                    System.out.println("删除记录完成，现在该表的数据如下：");
                    show_all_table(statementExcute);
                    break;
                }
                case 5:{
                    System.out.println("*****插入或修改字段*****");
                    do_one_in_SQL(statementExcuteUpdate,statementExcute);
                    System.out.println("插入或修改字段完成，现在该表的数据如下：");
                    show_all_table(statementExcute);
                    break;
                }
                case 6:{
                    System.out.println("当前字段有：");
                    int count = statementExcute.show_all_classname();
                    if(count == 4){
                        System.out.println("存在score字段，选择成绩统计方法：");
                        System.out.println("******1、成绩份数******");
                        System.out.println("******2、成绩总和******");
                        System.out.println("******3、平均成绩******");
                        System.out.println("******4、最高成绩******");
                        System.out.println("******5、最低成绩******");
                        int judge = input.nextInt();
                        input.nextLine();
                        switch (judge){
                            case 1:{
                                StatementExcute statementExcute5 = new StatementExcute(
                                        "SELECT COUNT(score) FROM student_x;",forname_x, url_x,user_x,password_x);
                                statementExcute5.count("");
                                break;
                            }
                            case 2:{
                                StatementExcute statementExcute5 = new StatementExcute(
                                        "SELECT SUM(score) FROM student_x;",forname_x, url_x,user_x,password_x);
                                statementExcute5.count("");
                                break;
                            }
                            case 3:{
                                StatementExcute statementExcute5 = new StatementExcute(
                                        "SELECT AVG(score) FROM student_x;",forname_x, url_x,user_x,password_x);
                                statementExcute5.count("");
                                break;
                            }
                            case 4:{
                                StatementExcute statementExcute5 = new StatementExcute(
                                        "SELECT MAX(score) FROM student_x;",forname_x, url_x,user_x,password_x);
                                statementExcute5.count("");
                                break;
                            }
                            case 5:{
                                StatementExcute statementExcute5 = new StatementExcute(
                                        "SELECT MIN(score) FROM student_x;",forname_x, url_x,user_x,password_x);
                                statementExcute5.count("");
                                break;
                            }
                            default:{
                                System.out.println("非法输入");
                                break;
                            }
                        }
                    }
                    if(count == 3){
                        System.out.println("不存在score字段，无法进行成绩统计");
                    }
                    break;
                }
                default:{
                    System.out.println("非法输入");
                    break;
                }
            }
        }
    }
}
