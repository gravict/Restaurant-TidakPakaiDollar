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
public class Account extends MyModel {
    private int id;
    private String username;
    private String password;
    private String phone_number;
    private String fullname;
    private String role;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    public Account() {
        super();
    }
    
    public Account(String username, String password, String phone_number, String fullname, String role) {
        super();
        this.username = username;
        this.password = password;
        this.phone_number = phone_number;
        this.fullname = fullname;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
            String query = "INSERT INTO account (username, password, phone_number, fullname, role, created_at, updated_at) " +
                           "VALUES ('" + this.username + "', '" + this.password + "', '" + this.phone_number + "', '" + 
                           this.fullname + "', '" + this.role + "', NOW(), NOW())";
            this.statement.executeUpdate(query);
            System.out.println("Data account berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Error insertData Account: " + e.getMessage());
        }
    }

    @Override
    public void updateData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "UPDATE account SET username = '" + this.username + "', password = '" + this.password + 
                           "', phone_number = '" + this.phone_number + "', fullname = '" + this.fullname + 
                           "', role = '" + this.role + "', updated_at = NOW() WHERE id = " + this.id;
            this.statement.executeUpdate(query);
            System.out.println("Data account berhasil diupdate!");
        } catch (Exception e) {
            System.out.println("Error updateData Account: " + e.getMessage());
        }
    }

    @Override
    public void deleteData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "DELETE FROM account WHERE id = " + this.id;
            this.statement.executeUpdate(query);
            System.out.println("Data account berhasil dihapus!");
        } catch (Exception e) {
            System.out.println("Error deleteData Account: " + e.getMessage());
        }
    }

}
