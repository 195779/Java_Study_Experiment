package EX7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


/**
 * @author 15328
 */
public class PreparedStatementExecuteDelete extends Main{
    public PreparedStatementExecuteDelete(String string_sql, String forname, String url, String user, String password) {
        super(string_sql, forname, url, user, password);
    }

    //这个类的main只提供单个类的功能测试，与整个程序的功能无关
    public static void main(String[] args) throws Exception{
        PreparedStatementExecuteDelete preparedStatementExecuteDelete = new PreparedStatementExecuteDelete(
                "delete from student_x where name=?;",forname_x,
                url_x,user_x,password_x);
        preparedStatementExecuteDelete.preparedstatementexecutedelete("");
    }

    public void preparedstatementexecutedelete(String sql)throws Exception{
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        PreparedStatement preparedStatement = connection.prepareStatement(this.string_sql);

        preparedStatement.setString(1,sql);

        preparedStatement.executeUpdate();

        if(preparedStatement != null) {
            preparedStatement.close();
        }
        if(connection != null) {
            connection.close();
        }

    }
}
