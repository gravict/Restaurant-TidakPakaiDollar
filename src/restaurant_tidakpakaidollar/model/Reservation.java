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
public class Reservation extends MyModel {
    private int id;
    private Timestamp start_reservation;
    private String reservation_status;
    private String order_status;
    private int number_guest;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Account account;
    private RestaurantTable restaurant_table;
    
    public Reservation() {
        super();
    }
    
    public Reservation(Timestamp start_reservation, String reservation_status, String order_status, int number_guest, Account account, RestaurantTable restaurant_table) {
        super();
        this.start_reservation = start_reservation;
        this.reservation_status = reservation_status;
        this.order_status = order_status;
        this.number_guest = number_guest;
        this.account = account;
        this.restaurant_table = restaurant_table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStart_reservation() {
        return start_reservation;
    }

    public void setStart_reservation(Timestamp start_reservation) {
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
    
    @Override
    public void insertData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "INSERT INTO reservation (start_reservation, reservation_status, order_status, number_guest, account_id, restaurant_table_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())");
                sql.setTimestamp(1, this.start_reservation);
                sql.setString(2, this.reservation_status);
                sql.setString(3, this.order_status);
                sql.setInt(4, this.number_guest);
                sql.setInt(5, this.account.getId());
                sql.setInt(6, this.restaurant_table.getId());
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
                sql.setTimestamp(1, this.start_reservation);
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
}
