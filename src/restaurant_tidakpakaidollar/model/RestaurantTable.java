/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

import java.sql.Timestamp;

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

    public RestaurantTable(int number, int capacity, String status) {
        super();
        this.number = number;
        this.capacity = capacity;
        this.status = status;
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
            this.statement = MyModel.conn.createStatement();
            // number dan capacity tidak butuh tanda kutip satu ('') karena tipe datanya INT (angka)
            String query = "INSERT INTO restaurant_table (number, capacity, status, created_at, updated_at) " +
                           "VALUES (" + this.number + ", " + this.capacity + ", '" + this.status + "', NOW(), NOW())";
            this.statement.executeUpdate(query);
            System.out.println("Data meja berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Error insertData RestaurantTable: " + e.getMessage());
        }
    }

    @Override
    public void updateData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "UPDATE restaurant_table SET number = " + this.number + ", capacity = " + this.capacity + 
                           ", status = '" + this.status + "', updated_at = NOW() WHERE id = " + this.id;
            this.statement.executeUpdate(query);
            System.out.println("Data meja berhasil diupdate!");
        } catch (Exception e) {
            System.out.println("Error updateData RestaurantTable: " + e.getMessage());
        }
    }

    @Override
    public void deleteData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "DELETE FROM restaurant_table WHERE id = " + this.id;
            this.statement.executeUpdate(query);
            System.out.println("Data meja berhasil dihapus!");
        } catch (Exception e) {
            System.out.println("Error deleteData RestaurantTable: " + e.getMessage());
        }
    }
}
