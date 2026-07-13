/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
/**
 *
 * @author LEGION
 */
public class Menu extends MyModel {

    private int id;
    private String name;
    private String category;
    private int stock;
    private int price;
    private String description;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Menu() {
        super();
    }

    public Menu(int _id, String _name, String _category, int _stock, int _price, String _description) {
        super();
        this.id = _id;
        this.name = _name;
        this.category = _category;
        this.stock = _stock;
        this.price = _price;
        this.description = _description;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
                    "INSERT INTO menu (name, category, stock, price, description, created_at, updated_at) VALUES (?, ?, 0, ?, ?, NOW(), NOW())");
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

    @Override
    public String viewListData() {       
        String menus = "";
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("SELECT * from menu");
            while (this.result.next()) { // looping hasil dari db
                menus += this.result.getInt("id") + ";" + 
                    this.result.getString("name") + ";" +
                    this.result.getString("category") + ";" +
                    this.result.getInt("price") + ";" +
                    this.result.getInt("stock") + ";" +
                    this.result.getString("description") + "#";
            }
        } catch (Exception e) {
            System.out.println("Error viewListData menu" + e);
        }
        return menus;
    }
    
    public String getMenuFiltered(String filterBy, String value) {
        String menus = "";
        try {
            String query = "SELECT * FROM menu WHERE " + filterBy + " LIKE ?";
            
            
            PreparedStatement sql = MyModel.conn.prepareStatement(query);
            sql.setString(1, "%" + value + "%");
            this.result = sql.executeQuery();

            while (this.result.next()) {
                menus += this.result.getInt("id") + ";"
                        + this.result.getString("name") + ";"
                        + this.result.getString("category") + ";"
                        + this.result.getInt("price") + ";"
                        + this.result.getInt("stock") + ";"
                        + this.result.getString("description") + "#";
            }
            sql.close();

        } catch (Exception e) {
            System.out.println("Error filter menu: " + e);
        }
        return menus;
    }
}
