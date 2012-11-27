/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.webservice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceRef;
import net.robertshippey.travelagency.core.reference.TravelCore_Service;

/**
 *
 * @author n0305434
 */
@WebService(serviceName = "TravelAgencyWebService")
public class TravelAgencyWebService {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/TravelAgency/TravelCore.wsdl")
    private TravelCore_Service service;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllFlights")
    public String getAllFlights(@WebParam(name = "currency") String currency) {
        //TODO write your implementation code here:
        String allFlights = getAllFlightsFromCore();
        return allFlights;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "makeBooking")
    public String makeBooking(@WebParam(name = "flightCode") String flightCode, @WebParam(name = "passengerName") String passengerName, @WebParam(name = "noOfSeats") String noOfSeats) {
        //TODO write your implementation code here:
        String bookingResult = makeBookingFromCore(flightCode, passengerName, noOfSeats);
        return bookingResult;
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
        String searchResults = searchFlightsFromCore(origin, desdination, date, directFlight);
        return searchResults;
    }

    private String getAllFlightsFromCore() {
        net.robertshippey.travelagency.core.reference.TravelCore port = service.getTravelCorePort();
        return port.getAllFlights();
    }

    private String makeBookingFromCore(java.lang.String flightCode, java.lang.String passengerName, java.lang.String noOfSeats) {
        net.robertshippey.travelagency.core.reference.TravelCore port = service.getTravelCorePort();
        return port.makeBooking(flightCode, passengerName, noOfSeats);
    }

    private String searchFlightsFromCore(java.lang.String origin, java.lang.String desdination, java.lang.String date, java.lang.String directFlight) {
        net.robertshippey.travelagency.core.reference.TravelCore port = service.getTravelCorePort();
        return port.searchFlights(origin, desdination, date, directFlight);
    }
}
