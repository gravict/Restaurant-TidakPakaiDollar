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
            int id = Integer.parseInt(data[1]); // Ambil ID
            String newUsername = data[2];       // Ambil Username Baru
            String fullname = data[3];
            String phone = data[4];
            String oldPass = data[5];
            String newPass = (data.length > 6) ? data[6] : "";

            return updateProfileDB(id, newUsername, fullname, phone, oldPass, newPass);
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
    private static String updateProfileDB(int id, String username, String fullname, String phone, String oldPass, String newPass) {
        com.restaurant.services.AccountWS_Service service = new com.restaurant.services.AccountWS_Service();
        com.restaurant.services.AccountWS port = service.getAccountWSPort();
        return port.updateProfile(id, username, fullname, phone, oldPass, newPass);
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
}
