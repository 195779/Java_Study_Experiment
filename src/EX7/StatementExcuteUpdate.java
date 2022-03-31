package EX7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author 15328
 */
public class StatementExcuteUpdate extends Main {

    StatementExcuteUpdate(String string_sql, String forname, String url, String user, String password){
        super(string_sql,forname,url,user,password);
    }

    //这个类的main只提供单个类的功能测试，与整个程序的功能无关
    public static void main(String[] args) throws Exception{
        StatementExcuteUpdate statementExcuteUpdate = new StatementExcuteUpdate(
                "select * from student_x;",forname_x, url_x,user_x,password_x);
        statementExcuteUpdate.statementexcuteupdate("");
    }

    public void statementexcuteupdate(String sql) throws  Exception {
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        Statement statement = connection.createStatement();

        statement.executeUpdate(sql);

        if(statement != null) {
            statement.close();
        }
        if(connection != null) {
            connection.close();
        }
    }
}
