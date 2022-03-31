package EX7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author 15328
 */
public class PreparedStatementExecuteInsert extends Main{
    public PreparedStatementExecuteInsert(String string_sql, String forname, String url, String user, String password) {
        super(string_sql, forname, url, user, password);
    }

    //这个类的main只提供单个类的功能测试，与整个程序的功能无关
    public static void main(String[] args) throws Exception{
        PreparedStatementExecuteInsert preparedStatementExecuteInsert = new PreparedStatementExecuteInsert(
                "insert into student_x values(?,?,?);",forname_x,
                url_x,user_x,password_x);
        preparedStatementExecuteInsert.preparedstatementexecuteinsert("Student3","male",19);
    }

    public void preparedstatementexecuteinsert(String name,String sex,int age) throws Exception{
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        PreparedStatement preparedStatement = connection.prepareStatement(this.string_sql);

        preparedStatement.setString(1,name);
        preparedStatement.setString(2,sex);
        preparedStatement.setInt(3,age);

        preparedStatement.executeUpdate();

        if(preparedStatement != null) {
            preparedStatement.close();
        }
        if(connection != null) {
            connection.close();
        }

    }


    public void preparedstatementexecuteinsert1(String name,String sex,int age,int score) throws Exception{
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        PreparedStatement preparedStatement = connection.prepareStatement(this.string_sql);

        preparedStatement.setString(1,name);
        preparedStatement.setString(2,sex);
        preparedStatement.setInt(3,age);
        preparedStatement.setInt(4,score);

        preparedStatement.executeUpdate();

        if(preparedStatement != null) {
            preparedStatement.close();
        }
        if(connection != null) {
            connection.close();
        }

    }
}
