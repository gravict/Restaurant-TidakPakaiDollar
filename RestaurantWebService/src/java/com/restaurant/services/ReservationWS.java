/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.restaurant.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import restaurant_tidakpakaidollar.model.*;

/**
 *
 * @author Giffert
 */
@WebService(serviceName = "ReservationWS")
public class ReservationWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createReservation")
    public String createReservation(@WebParam(name = "userId") int userId, @WebParam(name = "guest") int guest, @WebParam(name = "datetime") String datetime) {
        Reservation reservation = new Reservation();
        reservation.setAccountId(userId);
        reservation.setNumber_guest(guest);
        reservation.setStart_reservation(datetime);
        return reservation.createReservation();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllReservation")
    public String getAllReservation() {
        //TODO write your implementation code here:
        Reservation model = new Reservation();
        return model.viewListData();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cancelReservation")
    public String cancelReservation(@WebParam(name = "idReservasi") int idReservasi) {
        //TODO write your implementation code here:    
        Reservation model = new Reservation();
        return model.cancelReservation(idReservasi);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cekStatus")
    public String cekStatus(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        Reservation model = new Reservation();
        return model.cekStatus(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateStatus")
    public String updateStatus(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        Reservation model = new Reservation();
        return model.updateStatus(id);
    }
}
