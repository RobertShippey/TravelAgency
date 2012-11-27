/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.webservice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author n0305434
 */
@WebService(serviceName = "TravelAgencyWebService")
public class TravelAgencyWebService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllFlights")
    public String getAllFlights(@WebParam(name = "currency") String currency) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "makeBooking")
    public String makeBooking(@WebParam(name = "flightCode") String flightCode, @WebParam(name = "passengerName") String passengerName, @WebParam(name = "noOfSeats") int noOfSeats) {
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
                                @WebParam(name = "directFlight") String directFlight, 
                                @WebParam(name = "currency") String currency) {
        //TODO write your implementation code here:
        return null;
    }
}
