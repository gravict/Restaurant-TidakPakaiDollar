/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author LEGION
 */
public class RestaurantTable extends MyModel{
    private int id;
    private int number;
    private int capacity;
    private String status;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    public RestaurantTable() {
        super();
    }

    public RestaurantTable(int _number, int _capacity, String _status) {
        super();
        this.number = _number;
        this.capacity = _capacity;
        this.status = _status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
    
    @Override
    public void insertData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "INSERT INTO restaurant_table (number, capacity, status, created_at, updated_at) VALUES (?, ?, ?, NOW(), NOW())");
                sql.setInt(1, this.number);
                sql.setInt(2, this.capacity);
                sql.setString(3, this.status);
                sql.executeUpdate();
                System.out.println("Data meja berhasil ditambahkan!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di insert data RestaurantTable: " + ex.getMessage());
        }
    }

    @Override
    public void updateData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE restaurant_table SET number = ?, capacity = ?, status = ?, updated_at = NOW() WHERE id = ?");
                sql.setInt(1, this.number);
                sql.setInt(2, this.capacity);
                sql.setString(3, this.status);
                sql.setInt(4, this.id);
                sql.executeUpdate();
                System.out.println("Data meja berhasil diupdate!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di update data RestaurantTable: " + ex.getMessage());
        }
    }

    @Override
    public void deleteData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "DELETE FROM restaurant_table WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                System.out.println("Data meja berhasil dihapus!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di delete data RestaurantTable: " + ex.getMessage());
        }
    }

    @Override
    public String viewListData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
