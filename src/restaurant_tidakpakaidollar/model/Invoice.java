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
public class Invoice extends MyModel{
    private int id;
    private int total_purchases;
    private Timestamp transaction_date;
    private Reservation reservation;
    
    public Invoice() {
        super();
    }
    
    public Invoice(int total_purchases, Timestamp transaction_date, Reservation reservation) {
        super();
        this.total_purchases = total_purchases;
        this.transaction_date = transaction_date;
        this.reservation = reservation;
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
    
    @Override
    public void insertData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "INSERT INTO invoice (total_purchases, transaction_date, reservation_id) " +
                           "VALUES (" + this.total_purchases + ", '" + this.transaction_date + "', " + this.reservation.getId() + ")";
            this.statement.executeUpdate(query);
            System.out.println("Data invoice berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Error insertData Invoice: " + e.getMessage());
        }
    }

    @Override
    public void updateData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "UPDATE invoice SET total_purchases = " + this.total_purchases + 
                           ", transaction_date = '" + this.transaction_date + "', reservation_id = " + this.reservation.getId() + 
                           " WHERE id = " + this.id;
            this.statement.executeUpdate(query);
            System.out.println("Data invoice berhasil diupdate!");
        } catch (Exception e) {
            System.out.println("Error updateData Invoice: " + e.getMessage());
        }
    }

    @Override
    public void deleteData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "DELETE FROM invoice WHERE id = " + this.id;
            this.statement.executeUpdate(query);
            System.out.println("Data invoice berhasil dihapus!");
        } catch (Exception e) {
            System.out.println("Error deleteData Invoice: " + e.getMessage());
        }
    }
}
