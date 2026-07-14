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
    private int menuId;
    private Menu menu;
    private int reservationId;
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

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    
    public String createOrderDetail() {
        if (this.reduceStock() && this.insertData().equals("SUCCESS")) {
            return "CREATE_ORDER_SUCCESS";
        }        
        return "CREATE_ORDER_FAILED";
    }
    
    public boolean reduceStock() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = MyModel.conn.prepareStatement(
                        "UPDATE menu SET stock = stock - ? WHERE id = ?"
                );
                sql.setInt(1, this.amount);
                sql.setInt(2, this.menuId);

                int result = sql.executeUpdate();
                sql.close();
                return result > 0;
            }
        } catch (Exception ex) {
            System.out.println("Error reduce stock: " + ex.getMessage());
        }
        return false;
    }
    
    @Override
    public String insertData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "INSERT INTO detail_order (menu_id, reservation_id, amount, subtotal) VALUES (?, ?, ?, ?)");
                sql.setInt(1, this.menuId);
                sql.setInt(2, this.reservationId);
                sql.setInt(3, this.amount);
                sql.setInt(4, this.subtotal);
                sql.executeUpdate();                
                sql.close();
                return("SUCCESS");
            }
        } catch (Exception ex) {
            System.out.println("Error di insert data DetailOrder: " + ex.getMessage());
        }
        return ("FAILED");
    }

    @Override
    public String updateData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE detail_order SET amount = ?, subtotal = ? WHERE menu_id = ? AND reservation_id = ?");
                sql.setInt(1, this.amount);
                sql.setInt(2, this.subtotal);
                sql.setInt(3, this.menu.getId());
                sql.setInt(4, this.reservation.getId());
                sql.executeUpdate();
                sql.close();
                return ("SUCCESS");                
            }
        } catch (Exception ex) {
            System.out.println("Error di update data DetailOrder: " + ex.getMessage());
        }
        return ("FAILED");
    }

    @Override
    public String deleteData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "DELETE FROM detail_order WHERE menu_id = ? AND reservation_id = ?");
                sql.setInt(1, this.menu.getId());
                sql.setInt(2, this.reservation.getId());
                sql.executeUpdate();
                System.out.println("Data detail pesanan berhasil dihapus!");
                sql.close();
                return ("SUCCESS");
            }
        } catch (Exception ex) {
            System.out.println("Error di delete data DetailOrder: " + ex.getMessage());
        }
        return ("FAILED");
    }

    @Override
    public String viewListData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getDetailsByReservation(int reservationId) {
        String res = "";
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT m.name, d.amount FROM detail_order d JOIN menu m ON d.menu_id = m.id WHERE d.reservation_id = ?");
                sql.setInt(1, reservationId);
                this.result = sql.executeQuery();
                while (this.result.next()) {
                    res += this.result.getString("name") + ";" + 
                           this.result.getInt("amount") + "#";
                }
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println("Error getDetailsByReservation: " + ex.getMessage());
        }
        return res;
    }
 }
