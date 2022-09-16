

import java.sql.*;

public class MyConnection {
    Connection con;
    Statement st;
    PreparedStatement ps;



    public MyConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public Connection openConnection() {
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","root@123");
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return con;
    }



    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Statement getStatement() {
        try {
            st = this.openConnection().createStatement();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return st;



    }

    public PreparedStatement getPreparedStatement(String sqlQuery) {

        try {
            ps=this.openConnection().prepareStatement(sqlQuery);
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return ps;
    }
}
