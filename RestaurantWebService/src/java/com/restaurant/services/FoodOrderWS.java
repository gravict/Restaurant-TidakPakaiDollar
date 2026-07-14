/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.restaurant.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import restaurant_tidakpakaidollar.model.DetailOrder;
import restaurant_tidakpakaidollar.model.Invoice;

/**
 *
 * @author Giffert
 */
@WebService(serviceName = "FoodOrderWS")
public class FoodOrderWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    DetailOrder model = new DetailOrder();
    Invoice invoice = new Invoice();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addOrderDetail")
    public String addOrderDetail(@WebParam(name = "menuId") int menuId, @WebParam(name = "reservationId") int reservationId, @WebParam(name = "amount") int amount, @WebParam(name = "subtotal") int subtotal) {
        //TODO write your implementation code here:
        model.setMenuId(menuId);
        model.setReservationId(reservationId);
        model.setAmount(amount);
        model.setSubtotal(subtotal);
        return model.createOrderDetail();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createInvoice")
    public String createInvoice(@WebParam(name = "reservationId") int reservationId, @WebParam(name = "total") int total) {
        //TODO write your implementation code here:
        invoice.setReservationId(reservationId);
        invoice.setTotal_purchases(total);
        invoice.setTransaction_date(Timestamp.valueOf(LocalDateTime.now()));
        return invoice.insertData();
    }

}
