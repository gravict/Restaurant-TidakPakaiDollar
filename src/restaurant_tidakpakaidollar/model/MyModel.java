/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
    
    public Connection _getConnection(){
        if(MyModel.conn == null){ //jika belum ada koneksi
            try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); //isi dari jar
                return DriverManager.getConnection("jdbc:mysql://localhost/ezparking_160424024",
                "root", ""); //memasukkan nama schemanya, user, dan password
            }catch (Exception ex){
                System.out.println("Error di connection " + ex.getMessage());
            }
        }
        return MyModel.conn;
    }
    
    public abstract void insertData();
    public abstract void updateData();
    public abstract void deleteData();
    public abstract ArrayList<Object> viewListData();
}
