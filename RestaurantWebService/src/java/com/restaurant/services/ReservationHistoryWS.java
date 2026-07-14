/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.restaurant.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import restaurant_tidakpakaidollar.model.DetailOrder;
import restaurant_tidakpakaidollar.model.Reservation;

/**
 *
 * @author stepl
 */
@WebService(serviceName = "ReservationHistoryWS")
public class ReservationHistoryWS {

    Reservation reserve = new Reservation();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "getOrderDetails")
    public String getOrderDetails(@WebParam(name = "reservationId") int reservationId) {
        DetailOrder detailOrder = new DetailOrder();
        return detailOrder.getDetailsByReservation(reservationId);
    }
}
