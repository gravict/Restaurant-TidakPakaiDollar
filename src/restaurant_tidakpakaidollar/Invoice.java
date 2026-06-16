/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar;

import java.sql.Timestamp;

/**
 *
 * @author LEGION
 */
public class Invoice {
    private int id;
    private int total_purchases;
    private Timestamp transaction_date;
    private Reservation reservation;

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
    
    public Invoice(int total_purchases, Timestamp transaction_date, Reservation reservation) {
        this.total_purchases = total_purchases;
        this.transaction_date = transaction_date;
        this.reservation = reservation;
    }
}
