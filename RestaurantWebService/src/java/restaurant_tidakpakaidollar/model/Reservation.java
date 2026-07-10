/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author LEGION
 */
public class Reservation extends MyModel {
    private int id;
    private String start_reservation;
    private String reservation_status;
    private String order_status;
    private int number_guest;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Account account;
    private int accountId;
    private RestaurantTable restaurant_table;
    private int tableId;
    
    public Reservation() {
        super();
    }
    
    public Reservation(String _start_reservation, String _reservation_status, String _order_status, int _number_guest, Account _account, RestaurantTable _restaurant_table) {
        super();
        this.start_reservation = _start_reservation;
        this.reservation_status = _reservation_status;
        this.order_status = _order_status;
        this.number_guest = _number_guest;
        this.account = _account;
        this.restaurant_table = _restaurant_table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_reservation() {
        return start_reservation;
    }

    public void setStart_reservation(String start_reservation) {
        this.start_reservation = start_reservation;
    }

    public String getReservation_status() {
        return reservation_status;
    }

    public void setReservation_status(String reservation_status) {
        this.reservation_status = reservation_status;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public int getNumber_guest() {
        return number_guest;
    }

    public void setNumber_guest(int number_guest) {
        this.number_guest = number_guest;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public RestaurantTable getRestaurant_table() {
        return restaurant_table;
    }

    public void setRestaurant_table(RestaurantTable restaurant_table) {
        this.restaurant_table = restaurant_table;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
    
    public String createReservation() {
        String response = "RESERVATION_FAILED";
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "SELECT t.* "
                        + "FROM restaurant_table t "
                        + "LEFT JOIN reservation r "
                        + "ON t.id = r.restaurant_table_id "
                        + "AND r.start_reservation = ? "
                        + "AND r.reservation_status IN ('PENDING','ACCEPTED') "
                        + "WHERE t.capacity BETWEEN ? AND ? "
                        + "AND r.id IS NULL "
                        + "ORDER BY t.capacity ASC "
                        + "LIMIT 1"
                );

                sql.setString(1, this.start_reservation);
                sql.setInt(2, this.number_guest);
                sql.setInt(3, this.number_guest + 3);                

                this.result = sql.executeQuery();
                if (this.result.next()) {
                    this.tableId = this.result.getInt("id");
                    this.setReservation_status("ACCEPTED");
                    this.setOrder_status("PENDING");
                    this.insertData();

                    PreparedStatement idSql = MyModel.conn.prepareStatement(
                            "SELECT LAST_INSERT_ID()"
                    );
                    ResultSet idResult = idSql.executeQuery();
                    int reservationId = 0;
                    if (idResult.next()) {
                        reservationId = idResult.getInt(1);
                    }
                    idResult.close();
                    idSql.close();
                    response = "RESERVATION_SUCCESS;" + reservationId;
                }
                sql.close();
            }
        } catch (SQLException ex) {
            response = "RESERVATION_FAILED";
        }
        return response;
    }
    
    @Override
    public void insertData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "INSERT INTO reservation (start_reservation, reservation_status, order_status, number_guest, account_id, restaurant_table_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())");
                sql.setString(1, this.start_reservation);
                sql.setString(2, this.reservation_status);
                sql.setString(3, this.order_status);
                sql.setInt(4, this.number_guest);
                sql.setInt(5, this.accountId);
                sql.setInt(6, this.tableId);
                sql.executeUpdate();
                System.out.println("Data reservasi berhasil ditambahkan!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di insert data Reservation: " + ex.getMessage());
        }
    }

    @Override
    public void updateData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE reservation SET start_reservation = ?, reservation_status = ?, order_status = ?, number_guest = ?, account_id = ?, restaurant_table_id = ?, updated_at = NOW() WHERE id = ?");
                sql.setString(1, this.start_reservation);
                sql.setString(2, this.reservation_status);
                sql.setString(3, this.order_status);
                sql.setInt(4, this.number_guest);
                sql.setInt(5, this.account.getId());
                sql.setInt(6, this.restaurant_table.getId());
                sql.setInt(7, this.id);
                sql.executeUpdate();
                System.out.println("Data reservasi berhasil diupdate!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di update data Reservation: " + ex.getMessage());
        }
    }

    @Override
    public void deleteData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "DELETE FROM reservation WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                System.out.println("Data reservasi berhasil dihapus!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di delete data Reservation: " + ex.getMessage());
        }
    }

    @Override
    public String viewListData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
