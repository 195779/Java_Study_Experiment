package EX7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author 15328
 * 使用Statement执行SQL语句-----excuteQuery查询
 */
public class StatementQuery extends Main {
    StatementQuery(String string_sql, String forname, String url, String user, String password){
        super(string_sql,forname,url,user,password);
    }

    //这个类的main只提供单个类的功能测试，与整个程序的功能无关
    public static void main(String[] args) throws Exception{
        StatementQuery statementQuery = new StatementQuery("select * from d_class;",
                forname_x,url_x,user_x,password_x);
        statementQuery.statementquery();
    }
    public  void statementquery() throws Exception{
        /**加载驱动*/
        Class.forName(this.forname);
        /**使用DriveManager获取数据库连接*/
        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);
        /**使用Connection来创建一个Statement对象*/
        Statement statement = connection.createStatement();
        /**excuteQuery执行SQL语句，返回查询结果集*/
        ResultSet resultSet = statement.executeQuery(string_sql);

        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+"\t"
                    +resultSet.getString(2)+"\t"
                    +resultSet.getString(3)+"\t"
                    +resultSet.getInt(4)+"\t");
        }
        if(resultSet != null) {
            resultSet.close();
        }
        if(statement != null) {
            statement.close();
        }
        if(connection != null) {
            connection.close();
        }
    }
}
