package EX7;

import java.sql.*;
import java.util.Objects;

/**
 * @author 15328
 */
public class StatementExcute extends Main {
    public StatementExcute(String string_sql, String forname, String url, String user, String password) {
        super(string_sql, forname, url, user, password);
    }

    //这个类的main只提供单个类的功能测试，与整个程序的功能无关
    public static void main(String[] args) throws Exception {
        StatementExcute statementExcute = new StatementExcute("select * from student_x;",forname_x,
                url_x,user_x,password_x);
        statementExcute.statementexcute("");
    }
    public int show_all_classname()throws Exception {
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        Statement statement = connection.createStatement();


        boolean hasResultSet = statement.execute(this.string_sql);
        int return_int = 0;

        if(hasResultSet){
            ResultSet resultSet = statement.getResultSet();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            return_int = resultSetMetaData.getColumnCount();
            for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                System.out.print(resultSetMetaData.getColumnName(i)+"\t");
            }
            System.out.println();
            if(resultSet != null) {
                resultSet.close();
            }
        }
        if(statement != null) {
            statement.close();
        }
        if(connection != null) {
            connection.close();
        }
        return return_int;
    }

    public void statementexcute(String sql) throws Exception {
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        Statement statement = connection.createStatement();

        if(Objects.equals(this.string_sql, "")){
            this.string_sql = sql;
        }

        boolean hasResultSet = statement.execute(this.string_sql);
        if(hasResultSet){

            ResultSet resultSet = statement.getResultSet();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                System.out.print(resultSetMetaData.getColumnName(i)+"\t");
            }
            System.out.println();

            //System.out.println("getColumnCount: "+resultSetMetaData.getColumnCount());
            while(resultSet.next()){
               String show ="";
                for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                    if("java.lang.String".equals(resultSetMetaData.getColumnClassName(i))){
                        show += resultSet.getString(i) + "\t";
                    }
                    if("java.lang.Integer".equals(resultSetMetaData.getColumnClassName(i))){
                        show += resultSet.getInt(i) + "\t";
                    }
                }
                System.out.println(show);
               // System.out.println(resultSet.getInt(1));
            }
            if(resultSet != null) {
                resultSet.close();
            }
        }
        if(statement != null) {
            statement.close();
        }
        if(connection != null) {
            connection.close();
        }

    }


    public void count(String sql)throws Exception{
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        Statement statement = connection.createStatement();

        if(Objects.equals(this.string_sql, "")){
            this.string_sql = sql;
        }

        boolean hasResultSet = statement.execute(this.string_sql);

        if(hasResultSet){
            ResultSet resultSet = statement.getResultSet();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                System.out.print(resultSetMetaData.getColumnName(i)+"\t");
            }
            System.out.println();
            //System.out.println("getColumnCount: "+resultSetMetaData.getColumnCount());
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }
            if(resultSet != null) {
                resultSet.close();
            }
        }
        if(statement != null) {
            statement.close();
        }
        if(connection != null) {
            connection.close();
        }
    }

}
