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
            this.statement = MyModel.conn.createStatement();
            String query = "INSERT INTO menu (name, category, price, description, created_at, updated_at) "
                    + "VALUES ('" + this.name + "', '" + this.category + "', " + this.price + ", '"
                    + this.description + "', NOW(), NOW())";
            this.statement.executeUpdate(query);
            System.out.println("Data menu berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Error insertData Menu: " + e.getMessage());
        }
    }

    @Override
    public void updateData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "UPDATE menu SET name = '" + this.name + "', category = '" + this.category
                    + "', price = " + this.price + ", description = '" + this.description
                    + "', updated_at = NOW() WHERE id = " + this.id;
            this.statement.executeUpdate(query);
            System.out.println("Data menu berhasil diupdate!");
        } catch (Exception e) {
            System.out.println("Error updateData Menu: " + e.getMessage());
        }
    }

    @Override
    public void deleteData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "DELETE FROM menu WHERE id = " + this.id;
            this.statement.executeUpdate(query);
            System.out.println("Data menu berhasil dihapus!");
        } catch (Exception e) {
            System.out.println("Error deleteData Menu: " + e.getMessage());
        }
    }
}
