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
    
    public Account(String _username, String _password, String _phone_number, String _fullname, String _role) {
        super();
        this.username = _username;
        this.password = _password;
        this.phone_number = _phone_number;
        this.fullname = _fullname;
        this.role = _role;
    }
    
    public Account(String _username, String _password, String _phone_number, String _fullname, String _role, String _repeatPassword) {
        super();
        this.username = _username;
        this.password = _password;
        this.phone_number = _phone_number;
        this.fullname = _fullname;
        this.role = _role;
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
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "INSERT INTO account (username, password, phone_number, fullname, role, created_at, updated_at) VALUES (?, SHA2(?,256), ?, ?, ?, UTC_TIMESTAMP() + INTERVAL 7 HOUR, UTC_TIMESTAMP() + INTERVAL 7 HOUR)");
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                sql.setString(3, this.phone_number);
                sql.setString(4, this.fullname);
                sql.setString(5, this.role);
                sql.executeUpdate();
                System.out.println("Data account berhasil ditambahkan!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di insert data Account: " + ex.getMessage());
        }
    }

    @Override
    public void updateData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE account SET username = ?, password = SHA2(?,256), phone_number = ?, fullname = ?, role = ?, updated_at = NOW() WHERE id = ?");
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                sql.setString(3, this.phone_number);
                sql.setString(4, this.fullname);
                sql.setString(5, this.role);
                sql.setInt(6, this.id);
                sql.executeUpdate();
                System.out.println("Data account berhasil diupdate!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di update data Account: " + ex.getMessage());
        }
    }
    
    public String getDetails(String pUsername) {
        String profiles = "";
        try {    
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "SELECT * FROM account WHERE username = ?");
                System.out.println(pUsername);
                sql.setString(1, pUsername);
                this.result = sql.executeQuery();
                
                if (this.result.next()) {
                    profiles = this.result.getInt("id") + ";" + 
                            this.result.getString("fullname") + ";" + 
                            this.result.getString("phone_number");
                }                      
                sql.close();
            }
            
        } catch (Exception ex) {
            System.out.println("Error di getDetails Account: " + ex.getMessage());
        }
        System.out.println(profiles);
        return profiles;
    }

    @Override
    public void deleteData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "DELETE FROM account WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                System.out.println("Data account berhasil dihapus!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di delete data Account: " + ex.getMessage());
        }
    }
    
    public ArrayList<Object> checkUsername() {
        ArrayList<Object> collections = new ArrayList<>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT * FROM account WHERE username = ?");
                sql.setString(1, this.username);
                this.result = sql.executeQuery();
                
                while (this.result.next()) {
                    Account temp = new Account(
                            this.result.getString("username"),
                            this.result.getString("password"),
                            this.result.getString("phone_number"),
                            this.result.getString("fullname"),
                            this.result.getString("role")
                    );
                    temp.setId(this.result.getInt("id"));
                    collections.add(temp);
                }
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error checkUsername: " + e.getMessage());
        }
        return collections;
    }
    public boolean register(String username, String password) {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement cekSql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT * FROM account WHERE username = ?"
                );
                cekSql.setString(1, username);
                this.result = cekSql.executeQuery();
                if (this.result.next()) {
                    System.out.println("Register ditolak: Username sudah terpakai.");
                    cekSql.close();
                    return false;
                }
                cekSql.close();
                
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "INSERT INTO account(username, password) VALUES(?,SHA2(?,256))"
                );
                sql.setString(1, username);
                sql.setString(2, password);
                
                int rowsInserted = sql.executeUpdate();
                sql.close();
                
                return rowsInserted > 0;
            }
        } catch (Exception ex) {
            System.out.println("Error di register: " + ex.getMessage());
        }
        return false;
    }
    public String checkLogin(String username, String password) {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "SELECT * FROM account WHERE username = ? AND password = SHA2(?,256)"
                );
                sql.setString(1, username);
                sql.setString(2, password);

                this.result = sql.executeQuery();

                // Jika data ditemukan, langsung kembalikan role dan id
                if (this.result.next()) {
                    int pId = result.getInt("id");
                    String pRole = result.getString("role");
                    String pUsername = result.getString("username");
                    sql.close();

                    return pRole + ";" + pId + ";" + pUsername;
                }
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di checkLogin data: " + ex.getMessage());
        }
    
    // Jika tidak ditemukan atau terjadi error, kembalikan string kosong
    return ""; 
}
    public String updateProfile(int pId, String pFullname, String pPhone, String pOldPass, String pNewPass) {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement checkSql = MyModel.conn.prepareStatement("SELECT * FROM account WHERE id = ? AND password = SHA2(?,256)");
                checkSql.setInt(1, pId);
                checkSql.setString(2, pOldPass);
                this.result = checkSql.executeQuery();

                if (!this.result.next()) {
                    checkSql.close();
                    return "WRONG_PASSWORD";
                }
                checkSql.close();

                String query;
                PreparedStatement updateSql;

                if (pNewPass == null || pNewPass.isEmpty()) {
                    query = "UPDATE account SET fullname = ?, phone_number = ?, updated_at = NOW() WHERE id = ?";
                    updateSql = MyModel.conn.prepareStatement(query);
                    updateSql.setString(1, pFullname);
                    updateSql.setString(2, pPhone);
                    updateSql.setInt(3, pId);
                } else {
                    query = "UPDATE account SET fullname = ?, phone_number = ?, password = SHA2(?,256), updated_at = NOW() WHERE id = ?";
                    updateSql = MyModel.conn.prepareStatement(query);
                    updateSql.setString(1, pFullname);
                    updateSql.setString(2, pPhone);
                    updateSql.setString(3, pNewPass);
                    updateSql.setInt(4, pId);
                }

                int row = updateSql.executeUpdate();
                updateSql.close();

                if (row > 0) {
                    return "UPDATE_SUCCESS";
                }
            }
        } catch (Exception e) {
            System.out.println("Error di updateProfile Account Model: " + e.getMessage());
        }
        return "UPDATE_FAILED";
    }
    @Override
    public String viewListData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
