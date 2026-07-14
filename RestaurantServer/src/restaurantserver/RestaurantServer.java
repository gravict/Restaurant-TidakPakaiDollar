/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restaurantserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Giffert
 */

public class RestaurantServer implements Runnable{
    String chatClient, chatServer;
    Socket incoming;
    ServerSocket server;
    Thread serverThread;
    BufferedReader in;
    DataOutputStream out;
    ArrayList<HandleSocket> clients = new ArrayList<HandleSocket>();
    /**
     * @param args the command line arguments
     */
    public RestaurantServer() {
        try
        {            
            server = new ServerSocket(6000);
            
            System.out.println("Restaurant Server berjalan...");
            System.out.println("Menunggu client...");
            
            if (serverThread == null){
                serverThread = new Thread (this, "Chat");
                serverThread.start();
            }
        } catch(Exception e) {
            System.out.println("Error di constructor: " + e);
        }  
    }
    public static void main(String[] args) {
         new RestaurantServer();
    }
    
    public String handleRequest(String request, Socket client) {
        if (request.contains("GET_LOGIN;"))
        {
            String[] users = request.split(";");
            String user = checkLogin(users[1], users[2]);
            if (user.equals("")) 
            {
                return "LOGIN_FAILED";
            } 
            else 
            {
                return "LOGIN_SUCCESS;" + user;
            }
        }
        else if (request.contains("UPDATE_PROFILE;")) {
            String[] data = request.split(";");
            int id = Integer.parseInt(data[1]);
            String fullname = data[2];
            String phone = data[3];
            String oldPass = data[4];
            String newPass = (data.length > 5) ? data[5] : "";

            return updateProfileDB(id, fullname, phone, oldPass, newPass);
        }
        else if (request.contains("GET_REGISTER;")) 
        {
            String[] users = request.split(";");
            if (registerDB(users[1], users[2], users[3], users[4])) 
            {
                return "REGISTER_SUCCESS";
            } 
            else 
            {
                return "REGISTER_FAILED";
            }
        } 
        else if (request.contains("GET_DETAILS")) 
        {
            return getDetails(request.split(";")[1]);
        }
        else if (request.contains("CREATE_RESERVATION"))
        {
            String [] reservation = request.split(";");
            String result = createReservation(Integer.parseInt(reservation[1]), Integer.parseInt(reservation[2]), reservation[3]);
            return result;
        }
        else if (request.contains("GET_MENU")) 
        {
            if (request.equals("GET_MENU"))
            {
                return getAllMenus();
            }
            else 
            {
                String [] menus = request.split(";");
                return getMenuFiltered(menus[1], menus[2]);
            }
        }
        else if (request.contains("ADD_MENU;")) 
        {
            String[] data = request.split(";");
            return insertMenu(data[1], data[2], Integer.parseInt(data[3]), data[4]);
        }
        else if (request.contains("ADD_STOCK;"))
        {
            String[] data = request.split(";");
            return addStock(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        }
        else if (request.contains(";LOGIN")) 
        {
            System.out.println(request.split(";")[0] + " LOG IN");
            return "";
        } 
        else if (request.contains("LOGOUT")) 
        {
            System.out.println("Client logout: " + client.getInetAddress());
            removeClient(client);
            return "";
        } 
        else if (request.contains("GET_RESERVATION"))
        {
            return getAllReservation();
        }
        else if (request.equals("GET_TABLE"))
        {
            return getTableAll();
        }
        else if (request.contains("GET_TABLE_RESERVED;")) {
            String[] req = request.split(";");
            int id_table = Integer.parseInt(req[1]);
            return getTableReserved(id_table);
        }
        else if (request.contains("CANCEL_RESERVATION"))
        {
            String[] reservasi = request.split(";");
            int idReservasi = Integer.parseInt(reservasi[1]);
            return cancelReservation(idReservasi);
        }
        else if (request.contains("GET_ORDER_DETAILS;")) {
            String[] data = request.split(";");
            int idReservasi = Integer.parseInt(data[1]);
            return getOrderDetails(idReservasi);
        }
        else if (request.contains("CREATE_ORDER_DETAIL")) {
            String[] order = request.split(";");
            return addOrderDetail(Integer.parseInt(order[1]), Integer.parseInt(order[2]), Integer.parseInt(order[3]), Integer.parseInt(order[4]));
        }
        else if (request.contains("CREATE_INVOICE")) {
            String[] invoice = request.split(";");
            return createInvoice(Integer.parseInt(invoice[1]), Integer.parseInt(invoice[2]));
        }
        else if (request.contains("UPDATE_STATUS_RESERVATION")) {
            String[] reservasi = request.split(";");
            int idReservasi = Integer.parseInt(reservasi[1]);
            return updateStatus(idReservasi);
        }
        else 
        {
            return ""; 
        }
    }

    
    public void removeClient(Socket pSocket) {
        for (HandleSocket h : clients) {
            if (h.client.equals(pSocket)) {
                clients.remove(h);
                break;
            }
        }
        System.out.println("jumlah client: " + clients.size());
    }

    @Override
    public void run() {
        try {
            while (true) {
                incoming = server.accept();
                System.out.println("Client terkoneksi : " + incoming.getInetAddress());
                HandleSocket hs = new HandleSocket(this, incoming);
                hs.start();
                clients.add(hs);
                System.out.println("jumlah client: " + clients.size());
            }
        } catch (Exception ex) {
            System.out.println("Error di server: " + ex);
        }
    }
    private static Boolean registerDB(java.lang.String username, java.lang.String password, java.lang.String fullname, java.lang.String phone) {
        com.restaurant.services.AccountWS_Service service = new com.restaurant.services.AccountWS_Service();
        com.restaurant.services.AccountWS port = service.getAccountWSPort();
        return port.registerDB(username, password, fullname, phone);
    }

    private static String checkLogin(java.lang.String username, java.lang.String password) {
        com.restaurant.services.AccountWS_Service service = new com.restaurant.services.AccountWS_Service();
        com.restaurant.services.AccountWS port = service.getAccountWSPort();
        return port.checkLogin(username, password);
    }
    private static String updateProfileDB(int id, String fullname, String phone, String oldPass, String newPass) {
        com.restaurant.services.AccountWS_Service service = new com.restaurant.services.AccountWS_Service();
        com.restaurant.services.AccountWS port = service.getAccountWSPort();
        return port.updateProfile(id, fullname, phone, oldPass, newPass);
    }
    private static String getDetails(java.lang.String username) {
        com.restaurant.services.AccountWS_Service service = new com.restaurant.services.AccountWS_Service();
        com.restaurant.services.AccountWS port = service.getAccountWSPort();
        return port.getDetails(username);
    }

    private static String createReservation(int userId, int guest, java.lang.String datetime) {
        com.restaurant.services.ReservationWS_Service service = new com.restaurant.services.ReservationWS_Service();
        com.restaurant.services.ReservationWS port = service.getReservationWSPort();
        return port.createReservation(userId, guest, datetime);
    }

    private static String getAllMenus() {
        com.restaurant.services.MenuWS_Service service = new com.restaurant.services.MenuWS_Service();
        com.restaurant.services.MenuWS port = service.getMenuWSPort();
        return port.getAllMenus();
    }

    private static String getMenuFiltered(java.lang.String filterBy, java.lang.String value) {
        com.restaurant.services.MenuWS_Service service = new com.restaurant.services.MenuWS_Service();
        com.restaurant.services.MenuWS port = service.getMenuWSPort();
        return port.getMenuFiltered(filterBy, value);
    }

    private static String getAllReservation() {
        com.restaurant.services.ReservationWS_Service service = new com.restaurant.services.ReservationWS_Service();
        com.restaurant.services.ReservationWS port = service.getReservationWSPort();
        return port.getAllReservation();
    }
    
    private static String insertMenu(java.lang.String name, java.lang.String category, int price, java.lang.String description) {
        com.restaurant.services.MenuWS_Service service = new com.restaurant.services.MenuWS_Service();
        com.restaurant.services.MenuWS port = service.getMenuWSPort();
        return port.insertMenu(name, category, price, description);
    }

    private static String getTableAll() {
        com.restaurant.services.TableWS_Service service = new com.restaurant.services.TableWS_Service();
        com.restaurant.services.TableWS port = service.getTableWSPort();
        return port.getTableAll();
    }
    
    private static String getTableReserved(int idTable) {
        com.restaurant.services.TableWS_Service service = new com.restaurant.services.TableWS_Service();
        com.restaurant.services.TableWS port = service.getTableWSPort();
        return port.getTableReserved(idTable);
    }
    
    private static String addStock(int menuId, int addedStock) {
        com.restaurant.services.MenuWS_Service service = new com.restaurant.services.MenuWS_Service();
        com.restaurant.services.MenuWS port = service.getMenuWSPort();
        return port.addStock(menuId, addedStock);
    }

    private static String cancelReservation(int idReservasi) {
        com.restaurant.services.ReservationWS_Service service = new com.restaurant.services.ReservationWS_Service();
        com.restaurant.services.ReservationWS port = service.getReservationWSPort();
        return port.cancelReservation(idReservasi);
    }

    private static String getOrderDetails(int reservationId) {
        com.restaurant.services.ReservationHistoryWS_Service service = new com.restaurant.services.ReservationHistoryWS_Service();
        com.restaurant.services.ReservationHistoryWS port = service.getReservationHistoryWSPort();
        return port.getOrderDetails(reservationId);
    }

    private static String addOrderDetail(int menuId, int reservationId, int amount, int subtotal) {
        com.restaurant.services.FoodOrderWS_Service service = new com.restaurant.services.FoodOrderWS_Service();
        com.restaurant.services.FoodOrderWS port = service.getFoodOrderWSPort();
        return port.addOrderDetail(menuId, reservationId, amount, subtotal);
    }

    private static String createInvoice(int reservationId, int total) {
        com.restaurant.services.FoodOrderWS_Service service = new com.restaurant.services.FoodOrderWS_Service();
        com.restaurant.services.FoodOrderWS port = service.getFoodOrderWSPort();
        return port.createInvoice(reservationId, total);
    }

    private static String cekStatus(int id) {
        com.restaurant.services.ReservationWS_Service service = new com.restaurant.services.ReservationWS_Service();
        com.restaurant.services.ReservationWS port = service.getReservationWSPort();
        return port.cekStatus(id);
    }

    private static String updateStatus(int id) {
        com.restaurant.services.ReservationWS_Service service = new com.restaurant.services.ReservationWS_Service();
        com.restaurant.services.ReservationWS port = service.getReservationWSPort();
        return port.updateStatus(id);
    }
    
    

}
