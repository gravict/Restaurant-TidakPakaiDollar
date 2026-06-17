/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

/**
 *
 * @author LEGION
 */
public class DetailOrder {
    private Menu menu;
    private Reservation reservation;
    private int amount;
    private int subtotal;

    public DetailOrder(Menu menu, Reservation reservation, int amount, int subtotal) {
        this.menu = menu;
        this.reservation = reservation;
        this.amount = amount;
        this.subtotal = subtotal;
    }
    
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
 }
