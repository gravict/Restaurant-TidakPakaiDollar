/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                        + "AND r.reservation_status = 'ACCEPTED' "
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
                    if (this.insertData().equals("SUCCESS")) {
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
                        PreparedStatement updateTable = MyModel.conn.prepareStatement(
                            "UPDATE restaurant_table SET status = 'BOOKED' WHERE id = ?"
                        );
                        updateTable.setInt(1, this.tableId);
                        updateTable.executeUpdate();
                        updateTable.close();
                        
                        response = "RESERVATION_SUCCESS;" + reservationId;
                    }
                }
                sql.close();
            }
        } catch (SQLException ex) {
            response = "RESERVATION_FAILED";
        }
        return response;
    }

    @Override
    public String insertData() {
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
                sql.close();
                return "SUCCESS";
            }
        } catch (Exception ex) {
            System.out.println("Error di insert data Reservation: " + ex.getMessage());
        }
        return "FAILED";
    }

    @Override
    public String updateData() {
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
                sql.close();
                return "SUCCESS";
            }
        } catch (Exception ex) {
            System.out.println("Error di update data Reservation: " + ex.getMessage());
        }
        return "FAILED";
    }

    @Override
    public String deleteData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "DELETE FROM reservation WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                sql.close();
                return "SUCCESS";
            }
        } catch (Exception ex) {
            System.out.println("Error di delete data Reservation: " + ex.getMessage());
        }
        return "FAILED";
    }

    @Override
    public String viewListData() {
        String reserve = "";
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery(""
                    + "SELECT r.id, r.start_reservation, r.reservation_status, r.number_guest, a.username "
                    + "FROM reservation r INNER JOIN account a ON r.account_id = a.id");
            while (this.result.next()) {
                reserve += this.result.getInt("id") + ";"
                        + this.result.getString("start_reservation") + ";"
                        + this.result.getString("reservation_status") + ";"
                        + this.result.getInt("number_guest") + ";"
                        + this.result.getString("username") + "#";
            }
            System.out.println(reserve);
        } catch (Exception e) {
            System.out.println("Error ViewListData " + e);
        }
        return reserve;
    }

    public String cancelReservation(int idReservasi) {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "UPDATE reservation SET reservation_status = 'CANCELED', updated_at = NOW() WHERE id = ?");
                sql.setInt(1, idReservasi);
                sql.executeUpdate();
                sql.close();
                return "Cancel Reservation SUCCESS";
            }
        } catch (Exception ex) {
            System.out.println("Error di cancel data Reservation: " + ex.getMessage());
        }
        return "Cancel Reservation FAILED";
    }

    public String cekStatus(int id) {
        String status = "";
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("SELECT reservation_status FROM reservation WHERE id = " + id);
            this.result.next();
            status = this.result.getString("reservation_status");
        } catch (Exception e) {
            System.out.println("Error cek status " + e);
        }
        return status;
    }

    public String updateStatus(int id) {
        try {
            String statusReservation = cekStatus(id);
            if (!MyModel.conn.isClosed()) {
                this.statement = (Statement) MyModel.conn.createStatement();
                this.result = this.statement.executeQuery("SELECT order_status FROM reservation WHERE id = " + id);
                this.result.next();
                String orderStat = this.result.getString("order_status");

                if (orderStat.equals("SERVED")) {
                    if (statusReservation.equals("ACCEPTED")) {

                        PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                                "UPDATE reservation r INNER JOIN restaurant_table t ON r.restaurant_table_id = t.id "
                                + "SET r.reservation_status = 'ONGOING', r.updated_at = NOW(), t.status = 'USED' WHERE r.id = ?");
                        sql.setInt(1, id);
                        sql.executeUpdate();
                        sql.close();
                        return "Update Status Reservation to ONGOING SUCCESS";

                    } else if (statusReservation.equals("ONGOING")) {

                        PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                                "UPDATE reservation r INNER JOIN restaurant_table t ON r.restaurant_table_id = t.id "
                                + "SET r.reservation_status = 'FINISHED', r.updated_at = NOW() WHERE r.id = ?");
                        sql.setInt(1, id);
                        sql.executeUpdate();

                        this.result = this.statement.executeQuery("SELECT restaurant_table_id FROM reservation WHERE id = " + id);
                        this.result.next();
                        int idTable = this.result.getInt("restaurant_table_id");

                        sql = (PreparedStatement) MyModel.conn.prepareStatement(
                                "SELECT * FROM reservation WHERE restaurant_table_id = ? AND reservation_status = 'ACCEPTED' AND start_reservation  > NOW()");
                        sql.setInt(1, idTable);
                        this.result = sql.executeQuery();

                        if (this.result.next()) {
                            sql = (PreparedStatement) MyModel.conn.prepareStatement(
                                    "UPDATE restaurant_table SET status = 'BOOKED' WHERE id = ?");
                            sql.setInt(1, idTable);
                            sql.executeUpdate();
                        }
                        else
                        {
                            sql = (PreparedStatement) MyModel.conn.prepareStatement(
                                    "UPDATE restaurant_table SET status = 'AVAILABLE' WHERE id = ?");
                            sql.setInt(1, idTable);
                            sql.executeUpdate();
                        }

                        sql.close();
                        return "Update Status Reservation to FINISHED SUCCESS";

                    } else {
                        return "Status Reservasi sudah tidak bisa di update";
                    }
                } else {
                    return "Status order belum SERVED";
                }
            }
        } catch (Exception ex) {
            System.out.println("Error di cancel data Reservation: " + ex.getMessage());
        }
        return "Update Status Reservation FAILED";
    }
        
    public String viewOrderStatusData() {
        String order = "";
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery(""
                    + "SELECT r.id, r.order_status, a.fullname "
                    + "FROM reservation r INNER JOIN account a ON r.account_id = a.id");
            while (this.result.next()) {
                order += this.result.getInt("id") + ";"
                        + this.result.getString("order_status") + ";"
                        + this.result.getString("fullname") + "#";
            }
        } catch (Exception e) {
            System.out.println("Error ViewOrderStatusData " + e);
        }
        return order;
    }

    public String updateStatusOrder(int idReservasi) {
        try {
            String currentStatus = "";
            String sql = "SELECT r.order_status FROM reservation r INNER JOIN account a ON r.account_id = a.id WHERE r.id = ?;";
            PreparedStatement psql = MyModel.conn.prepareStatement(sql);
            psql.setInt(1, idReservasi);
            this.result = psql.executeQuery();
            while (this.result.next()) {
                currentStatus = this.result.getString("order_status");
            }

            String nextStatus = "";
            if (currentStatus.equals("PENDING")) {
                nextStatus = "PREPARING";
            } else if (currentStatus.equals("PREPARING")) {
                nextStatus = "READY";
            } else if (currentStatus.equals("READY")) {
                nextStatus = "SERVED";
            } else {
                return "SERVED Update Order Status FAILED";
            }

            if (!MyModel.conn.isClosed()) {
                PreparedStatement presql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "UPDATE reservation SET order_status = ?, updated_at = NOW() WHERE id = ?");
                presql.setString(1, nextStatus);
                presql.setInt(2, idReservasi);
                presql.executeUpdate();
                presql.close();
                return "Update Status SUCCESS";
            }
        } catch (Exception ex) {
            System.out.println("Error di update status_order table Reservation: " + ex.getMessage());
        }
        return "Update Order Status FAILED";
    }
    
}
