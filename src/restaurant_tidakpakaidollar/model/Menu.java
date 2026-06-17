/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 *
 * @author LEGION
 */
public class Menu extends MyModel {

    private int id;
    private String name;
    private String category;
    private int price;
    private String description;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Menu() {
        super();
    }

    public Menu(String name, String category, int price, String description) {
        super();
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                    "INSERT INTO menu (name, category, price, description, created_at, updated_at) VALUES (?, ?, ?, ?, NOW(), NOW())");
                sql.setString(1, this.name);
                sql.setString(2, this.category);
                sql.setInt(3, this.price);
                sql.setString(4, this.description);
                sql.executeUpdate();
                System.out.println("Data menu berhasil ditambahkan!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di insert data Menu: " + ex.getMessage());
        }
    }

    @Override
    public void updateData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE menu SET name = ?, category = ?, price = ?, description = ?, updated_at = NOW() WHERE id = ?");
                sql.setString(1, this.name);
                sql.setString(2, this.category);
                sql.setInt(3, this.price);
                sql.setString(4, this.description);
                sql.setInt(5, this.id);
                sql.executeUpdate();
                System.out.println("Data menu berhasil diupdate!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di update data Menu: " + ex.getMessage());
        }
    }

    @Override
    public void deleteData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "DELETE FROM menu WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                System.out.println("Data menu berhasil dihapus!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di delete data Menu: " + ex.getMessage());
        }
    }
}
