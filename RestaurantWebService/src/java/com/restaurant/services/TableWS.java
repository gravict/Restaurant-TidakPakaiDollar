/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.restaurant.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import restaurant_tidakpakaidollar.model.RestaurantTable;

/**
 *
 * @author Nicholas
 */
@WebService(serviceName = "TableWS")
public class TableWS {

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
    @WebMethod(operationName = "getTableAll")
    public String getTableAll() {
        //TODO write your implementation code here:
        RestaurantTable model = new RestaurantTable();        
        return model.viewListData();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTableReserved")
    public String getTableReserved(@WebParam(name = "id_table") int id_table) {
        //TODO write your implementation code here:        
        RestaurantTable model = new RestaurantTable();
        return model.viewTableReservation(id_table);
    }
}
