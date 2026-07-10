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
    Reservation reservation = new Reservation();
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createReservation")
    public String createReservation(@WebParam(name = "userId") int userId, @WebParam(name = "guest") int guest, @WebParam(name = "datetime") String datetime) {
        reservation.setAccountId(userId);
        reservation.setNumber_guest(guest);
        reservation.setStart_reservation(datetime);

        return reservation.createReservation();
    }
}
