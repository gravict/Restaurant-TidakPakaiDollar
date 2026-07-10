/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

import java.sql.PreparedStatement;
/**
 *
 * @author LEGION
 */
public class DetailOrder extends MyModel {
    private Menu menu;
    private Reservation reservation;
    private int amount;
    private int subtotal;

    public DetailOrder(){
        super();
    }
    
    public DetailOrder(Menu _menu, Reservation _reservation, int _amount, int _subtotal) {
        super();
        this.menu = _menu;
        this.reservation = _reservation;
        this.amount = _amount;
        this.subtotal = _subtotal;
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
    
    @Override
    public void insertData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "INSERT INTO detail_order (menu_id, reservation_id, amount, subtotal) VALUES (?, ?, ?, ?)");
                sql.setInt(1, this.menu.getId());
                sql.setInt(2, this.reservation.getId());
                sql.setInt(3, this.amount);
                sql.setInt(4, this.subtotal);
                sql.executeUpdate();
                System.out.println("Data detail pesanan berhasil ditambahkan!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di insert data DetailOrder: " + ex.getMessage());
        }
    }

    @Override
    public void updateData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE detail_order SET amount = ?, subtotal = ? WHERE menu_id = ? AND reservation_id = ?");
                sql.setInt(1, this.amount);
                sql.setInt(2, this.subtotal);
                sql.setInt(3, this.menu.getId());
                sql.setInt(4, this.reservation.getId());
                sql.executeUpdate();
                System.out.println("Data detail pesanan berhasil diupdate!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di update data DetailOrder: " + ex.getMessage());
        }
    }

    @Override
    public void deleteData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "DELETE FROM detail_order WHERE menu_id = ? AND reservation_id = ?");
                sql.setInt(1, this.menu.getId());
                sql.setInt(2, this.reservation.getId());
                sql.executeUpdate();
                System.out.println("Data detail pesanan berhasil dihapus!");
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error di delete data DetailOrder: " + ex.getMessage());
        }
    }

    @Override
    public String viewListData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 }
