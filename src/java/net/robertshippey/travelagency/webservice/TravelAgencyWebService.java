/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.webservice;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceRef;
import net.robertshippey.travelagency.core.reference.TravelCore_Service;
import net.robertshippey.travelagency.data.Fare;
import net.robertshippey.travelagency.data.Flight;
import net.robertshippey.travelagency.data.ListOfFlights;
import taha.currencyconversion.CurrencyConversionWSService;

/**
 *
 * @author n0305434
 */
@WebService(serviceName = "TravelAgencyWebService")
public class TravelAgencyWebService {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CurrencyConvertor/CurrencyConversionWSService.wsdl")
    private CurrencyConversionWSService service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/TravelAgency/TravelCore.wsdl")
    private TravelCore_Service service;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllFlights")
    public String getAllFlights(@WebParam(name = "currency") String currency) {
        //TODO write your implementation code here:
        String allFlights = getAllFlightsFromCore();
        StringReader sr = new StringReader(allFlights);
        ListOfFlights list = new ListOfFlights();
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(list.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            list = (ListOfFlights) unmarshaller.unmarshal(sr); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        Iterator<Flight> it = list.getFlight().iterator();
        while(it.hasNext()){
            Flight flight = it.next();
            Fare cost = flight.getFare();
            Fare newFare = convertFare(cost, currency);
            flight.setFare(newFare);
        }
        StringWriter sw = new StringWriter();
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(list.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(list, sw);
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
        return (sw.toString());
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
        StringReader sr = new StringReader(searchResults);
        ListOfFlights list = new ListOfFlights();
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(list.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            list = (ListOfFlights) unmarshaller.unmarshal(sr); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        Iterator<Flight> it = list.getFlight().iterator();
        while(it.hasNext()){
            Flight flight = it.next();
            Fare fare = flight.getFare();
            Fare newFare = convertFare(fare,currency);
            flight.setFare(newFare);
        }
        StringWriter sw = new StringWriter();
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(list.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(list, sw);
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
        return (sw.toString());
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

    private double getConversionRate(java.lang.String arg0, java.lang.String arg1) {
        taha.currencyconversion.CurrencyConversionWS port = service_1.getCurrencyConversionWSPort();
        return port.getConversionRate(arg0, arg1);
    }
    
    private Fare convertFare(Fare original, String desired){
        String originalCurrency = original.getCurrency();
        float originalValue = original.getAmount();
        double rate = this.getConversionRate(originalCurrency, desired);
        if(rate == -1){
            return original;
        }
        float newValue = (float) (rate * originalValue);
        Fare fare = new Fare();
        fare.setAmount(newValue);
        fare.setCurrency(desired);
        return fare;
    }
}
