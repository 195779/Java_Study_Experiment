package EX7;

import java.sql.*;


/**
 * @author 15328
 */
public class PreparedStatmentExecute extends Main {
    public PreparedStatmentExecute(String string_sql, String forname, String url, String user, String password) {
        super(string_sql, forname, url, user, password);
    }

    //这个类的main只提供单个类的功能测试，与整个程序的功能无关
    public static void main(String[] args) throws Exception{
        PreparedStatmentExecute preparedStatementExecute = new PreparedStatmentExecute(
                "select * from student_x where name=?;",forname_x,
                url_x,user_x,password_x);

    }

    public void preparedstatementexecute1(String name_sex) throws Exception{
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        PreparedStatement preparedStatement = connection.prepareStatement(this.string_sql);


        preparedStatement.setString(1,name_sex);

        boolean hasResultSet = preparedStatement.execute();

        if(hasResultSet){
            ResultSet resultSet = preparedStatement.getResultSet();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                System.out.print(resultSetMetaData.getColumnName(i)+"\t");
            }
            System.out.println();
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
            }
            if(resultSet !=null){
                resultSet.close();
            }
        }
        if(preparedStatement != null){
            preparedStatement.close();
        }
        if(connection != null){
            connection.close();
        }
    }

    public void preparedstatementexecute2(int age_score) throws Exception{
        Class.forName(this.forname);

        Connection connection = DriverManager.getConnection(this.url,this.user,this.password);

        PreparedStatement preparedStatement = connection.prepareStatement(this.string_sql);

        preparedStatement.setInt(1,age_score);

        boolean hasResultSet = preparedStatement.execute();

        if(hasResultSet){
            ResultSet resultSet = preparedStatement.getResultSet();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                System.out.print(resultSetMetaData.getColumnName(i)+"\t");
            }
            System.out.println();
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
            }
            if(resultSet !=null){
                resultSet.close();
            }
        }
        if(preparedStatement != null){
            preparedStatement.close();
        }
        if(connection != null){
            connection.close();
        }
    }
}
