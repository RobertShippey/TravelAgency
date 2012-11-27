/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.core;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author n0305434
 */
@WebService(serviceName = "TravelCore")
public class TravelCore {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "makeBooking")
    public String makeBooking(@WebParam(name = "flightCode") String flightCode, @WebParam(name = "passengerName") String passengerName, @WebParam(name = "noOfSeats") String noOfSeats) {
        //TODO write your implementation code here:
        return null;
    }
    
     /**
     * Web service operation
     */
    @WebMethod(operationName = "searchFlights")
    public String searchFlights(@WebParam(name = "origin") String origin, 
                                @WebParam(name = "desdination") String desdination, 
                                @WebParam(name = "date") String date, 
                                @WebParam(name = "directFlight") String directFlight) {
        //TODO write your implementation code here:
        return null;
    }
    
     /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllFlights")
    public String getAllFlights() {
        //TODO write your implementation code here:
        return null;
    }
}
