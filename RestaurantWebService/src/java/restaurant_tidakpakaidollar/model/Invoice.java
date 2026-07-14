/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
/**
 *
 * @author LEGION
 */
public class Invoice extends MyModel{
    private int id;
    private int total_purchases;
    private Timestamp transaction_date;
    private Reservation reservation;
    private int reservationId;
    
    public Invoice() {
        super();
    }
    
    public Invoice(int _total_purchases, Timestamp _transaction_date, Reservation _reservation) {
        super();
        this.total_purchases = _total_purchases;
        this.transaction_date = _transaction_date;
        this.reservation = _reservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal_purchases() {
        return total_purchases;
    }

    public void setTotal_purchases(int total_purchases) {
        this.total_purchases = total_purchases;
    }

    public Timestamp getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Timestamp transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    
    @Override
    public String insertData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "INSERT INTO invoice (total_purchases, transaction_date, reservation_id) VALUES (?, ?, ?)");
                sql.setInt(1, this.total_purchases);
                sql.setTimestamp(2, this.transaction_date);
                sql.setInt(3, this.reservationId);
                sql.executeUpdate();
                sql.close();
                return "INVOICE_SUCCESS";
            }
        } catch (Exception ex) {
            System.out.println("Error di insert data Invoice: " + ex.getMessage());
        }
        return "INVOICE_FAILED";
    }

    @Override
    public String updateData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE invoice SET total_purchases = ?, transaction_date = ?, reservation_id = ? WHERE id = ?");
                sql.setInt(1, this.total_purchases);
                sql.setTimestamp(2, this.transaction_date);
                sql.setInt(3, this.reservation.getId());
                sql.setInt(4, this.id);
                sql.executeUpdate();
                System.out.println("Data invoice berhasil diupdate!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di update data Invoice: " + ex.getMessage());
        }
        return "";
    }

    @Override
    public String deleteData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "DELETE FROM invoice WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                System.out.println("Data invoice berhasil dihapus!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di delete data Invoice: " + ex.getMessage());
        }
        return"";
    }

    @Override
    public String viewListData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String getInvoice(int reservationId) {
        String invoice = "";
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "SELECT * FROM invoice WHERE reservation_id = ?");
                sql.setInt(1, reservationId);
                this.result = sql.executeQuery();
                if (this.result.next()) {
                    this.id = this.result.getInt("id");
                    this.total_purchases = this.result.getInt("total_purchases");
                    this.transaction_date = this.result.getTimestamp("transaction_date");
                    
                    invoice = this.id + ";" + this.total_purchases + ";" + this.transaction_date.toString();
                }
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di get Invoice: " + ex.getMessage());
        }
        return invoice;
    }
    public String getOrderDetailsByInvoice(int reservationId) {
        String res = "";
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "SELECT m.name, d.amount, m.price, d.subtotal FROM detail_order d JOIN menu m ON d.menu_id = m.id WHERE d.reservation_id = ?");
                sql.setInt(1, reservationId);
                this.result = sql.executeQuery();
                while (this.result.next()) {
                    res += this.result.getString("name") + ";" + 
                           this.result.getInt("amount") + ";" +
                           this.result.getInt("price") + ";" +
                           this.result.getInt("subtotal") + "#";
                }
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error getDetailsByReservation: " + ex.getMessage());
        }
        return res;
    }
}
