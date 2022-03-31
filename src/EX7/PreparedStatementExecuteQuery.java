package EX7;

import java.sql.*;

/**
 * @author 15328
 */
public class PreparedStatementExecuteQuery extends Main{
    public PreparedStatementExecuteQuery(String string_sql, String forname, String url, String user, String password) {
        super(string_sql, forname, url, user, password);
    }

    //这个类的main只提供单个类的功能测试，与整个程序的功能无关
    public static void main(String[] args) throws Exception{
        PreparedStatementExecuteQuery preparedStatementExecuteQuery = new PreparedStatementExecuteQuery(
                "select * from student_x where age=?;",forname_x,
                url_x,user_x,password_x);
        preparedStatementExecuteQuery.preparedstatementexecutequery1("");
    }
    public void preparedstatementexecutequery1(String name_sex) throws Exception{
        /**只按姓名或者性别查询*/
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        PreparedStatement preparedStatement = connection.prepareStatement(this.string_sql);

        preparedStatement.setString(1,name_sex);

        ResultSet resultSet = preparedStatement.executeQuery();


        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+"\t"
                    +resultSet.getString(2)+"\t"
                    +resultSet.getInt(3)+"\t");
        }
        if(resultSet != null) {
            resultSet.close();
        }
        if(preparedStatement != null) {
            preparedStatement.close();
        }
        if(connection != null) {
            connection.close();
        }
    }

    public void preparedstatementexecutequery2(int age) throws Exception{
        /**只按年龄查询*/
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        PreparedStatement preparedStatement = connection.prepareStatement(this.string_sql);

        preparedStatement.setInt(1,age);

        ResultSet resultSet = preparedStatement.executeQuery();


        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+"\t"
                    +resultSet.getString(2)+"\t"
                    +resultSet.getInt(3)+"\t");
        }
        if(resultSet != null) {
            resultSet.close();
        }
        if(preparedStatement != null) {
            preparedStatement.close();
        }
        if(connection != null) {
            connection.close();
        }
    }
}
