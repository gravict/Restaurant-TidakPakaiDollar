/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

import restaurant_tidakpakaidollar.model.Account;
import java.sql.Timestamp;

/**
 *
 * @author LEGION
 */
public class Reservation {
    private int id;
    private Timestamp start_reservation;
    private String reservation_status;
    private String order_status;
    private int number_guest;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Account account;
    private RestaurantTable restaurant_table;
    
    public Reservation(Timestamp start_reservation, String reservation_status, String order_status, int number_guest, Account account, RestaurantTable restaurant_table) {
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
}
