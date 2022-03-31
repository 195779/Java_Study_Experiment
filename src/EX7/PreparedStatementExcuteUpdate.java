package EX7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author 15328
 */
public class PreparedStatementExcuteUpdate extends Main{
    public PreparedStatementExcuteUpdate(String string_sql, String forname, String url, String user, String password) {
        super(string_sql, forname, url, user, password);
    }

    //这个类的main只提供单个类的功能测试，与整个程序的功能无关
    public static void main(String[] args) throws Exception{
        PreparedStatementExcuteUpdate preparedStatementExcuteUpdate = new PreparedStatementExcuteUpdate(
                "update student_x set sex=? where name=?",forname_x,
                url_x,user_x,password_x);
        preparedStatementExcuteUpdate.preparedstatementexecuteupdate("","");
    }

    public void preparedstatementexecuteupdate(String sql1,String sql2) throws Exception{
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        PreparedStatement preparedStatement = connection.prepareStatement(this.string_sql);

        preparedStatement.setString(1,sql1);
        preparedStatement.setString(2,sql2);

        preparedStatement.executeUpdate();

        if(preparedStatement != null) {
            preparedStatement.close();
        }
        if(connection != null) {
            connection.close();
        }

    }

    public void prepreparedstatementexecuteupdate2(int sql1,String sql2) throws Exception{
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        PreparedStatement preparedStatement = connection.prepareStatement(this.string_sql);

        preparedStatement.setInt(1,sql1);
        preparedStatement.setString(2,sql2);

        preparedStatement.executeUpdate();

        if(preparedStatement != null) {
            preparedStatement.close();
        }
        if(connection != null) {
            connection.close();
        }

    }
}
