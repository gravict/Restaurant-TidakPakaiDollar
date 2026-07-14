/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.restaurant.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import restaurant_tidakpakaidollar.model.Menu;

/**
 *
 * @author Giffert
 */
@WebService(serviceName = "MenuWS")
public class MenuWS {

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
    @WebMethod(operationName = "getAllMenus")
    public String getAllMenus() {
        //TODO write your implementation code here:
        Menu model = new Menu();
        return model.viewListData();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getMenuFiltered")
    public String getMenuFiltered(@WebParam(name = "filterBy") String filterBy, @WebParam(name = "value") String value) {
        //TODO write your implementation code here:
        Menu model = new Menu();
        return model.getMenuFiltered(filterBy, value);
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertMenu")
    public String insertMenu(@WebParam(name = "name") String name, @WebParam(name = "category") String category, @WebParam(name = "price") int price, @WebParam(name = "description") String description) {
        Menu newMenu = new Menu(0, name, category, 0, price, description);
        return newMenu.insertData();
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "addStock")
    public String addStock(@WebParam(name = "menuId") int menuId, @WebParam(name = "addedStock") int addedStock) {
        Menu menuModel = new Menu();
        boolean success = menuModel.addStock(menuId, addedStock);
        if (success) {
            return "SUCCESS";
        } else {
            return "FAILED";
        }
    }
}
