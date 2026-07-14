/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author LEGION
 */
public abstract class MyModel {
    protected static Connection conn;
    protected Statement statement;
    protected ResultSet result;
    
    public MyModel(){
        this.conn = this._getConnection();
        this.statement = null;
        this.result = null;
    }
    
    public Connection _getConnection() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://tidakpakedollar.mysql.database.azure.com:3306/restaurant_tidakpakaidollar"
                   + "?useSSL=false"
                   + "&serverTimezone=Asia/Jakarta";

        String user = "cipenk";
        String password = "TidakPakeDollar_123";

        return DriverManager.getConnection(url, user, password);

    } catch (Exception ex) {
        ex.printStackTrace();
        return null;
    }
}
    
    public abstract String insertData();
    public abstract String updateData();
    public abstract String deleteData();
    public abstract String viewListData();
}
