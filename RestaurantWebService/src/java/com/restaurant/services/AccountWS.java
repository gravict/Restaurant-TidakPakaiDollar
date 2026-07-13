/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.restaurant.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import restaurant_tidakpakaidollar.model.Account;

/**
 *
 * @author LEGION
 */
@WebService(serviceName = "AccountWS")
public class AccountWS {

    Account model = new Account();

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "registerDB")
    public Boolean registerDB(@WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "fullname") String fullname,
            @WebParam(name = "phone") String phone) {
        model = new Account(username, password, phone, fullname, "CUSTOMER");
        if (!model.checkUsername().isEmpty()) {
            return false;
        }
        model.insertData();
        return true;
    }

    @WebMethod(operationName = "checkLogin")
    public String checkLogin(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        return model.checkLogin(username, password);
    }

    @WebMethod(operationName = "updateProfile")
    public String updateProfile(
            @WebParam(name = "id") int id,
            @WebParam(name = "fullname") String fullname,
            @WebParam(name = "phone") String phone,
            @WebParam(name = "oldPassword") String oldPassword,
            @WebParam(name = "newPassword") String newPassword) {

        return model.updateProfile(id, fullname, phone, oldPassword, newPassword);
    }

    @WebMethod(operationName = "getDetails")
    public String getDetails(@WebParam(name = "username") String username) {
        //TODO write your implementation code here:
        return model.getDetails(username);
    }
}
