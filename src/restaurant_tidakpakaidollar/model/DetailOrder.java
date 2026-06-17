/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant_tidakpakaidollar.model;

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
    
    public DetailOrder(Menu menu, Reservation reservation, int amount, int subtotal) {
        super();
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
    
    @Override
    public void insertData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "INSERT INTO detail_order (menu_id, reservation_id, amount, subtotal) " +
                           "VALUES (" + this.menu.getId() + ", " + this.reservation.getId() + ", " + 
                           this.amount + ", " + this.subtotal + ")";
            this.statement.executeUpdate(query);
            System.out.println("Data detail pesanan berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Error insertData DetailOrder: " + e.getMessage());
        }
    }

    @Override
    public void updateData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "UPDATE detail_order SET amount = " + this.amount + ", subtotal = " + this.subtotal + 
                           " WHERE menu_id = " + this.menu.getId() + " AND reservation_id = " + this.reservation.getId();
            this.statement.executeUpdate(query);
            System.out.println("Data detail pesanan berhasil diupdate!");
        } catch (Exception e) {
            System.out.println("Error updateData DetailOrder: " + e.getMessage());
        }
    }

    @Override
    public void deleteData() {
        try {
            this.statement = MyModel.conn.createStatement();
            String query = "DELETE FROM detail_order WHERE menu_id = " + this.menu.getId() + 
                           " AND reservation_id = " + this.reservation.getId();
            this.statement.executeUpdate(query);
            System.out.println("Data detail pesanan berhasil dihapus!");
        } catch (Exception e) {
            System.out.println("Error deleteData DetailOrder: " + e.getMessage());
        }
    }
 }
