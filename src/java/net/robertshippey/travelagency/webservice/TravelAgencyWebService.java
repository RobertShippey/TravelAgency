/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.webservice;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;
import net.robertshippey.travelagency.core.reference.TravelCore_Service;
import net.robertshippey.travelagency.data.Fare;
import net.robertshippey.travelagency.data.Flight;
import net.robertshippey.travelagency.data.ListOfFlights;
import taha.currencyconversion.CurrencyConversionWS;
import taha.currencyconversion.CurrencyConversionWSService;

/**
 *
 * @author n0305434
 */
@WebService(serviceName = "TravelAgencyWebService")
public class TravelAgencyWebService {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CurrencyConvertor/CurrencyConversionWSService.wsdl")
    private CurrencyConversionWSService currencyConverter;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/TravelAgency/TravelCore.wsdl")
    private TravelCore_Service travelCore;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllFlights")
    public String getAllFlights(@WebParam(name = "currency") String currency) {
        //TODO write your implementation code here:
        net.robertshippey.travelagency.core.reference.TravelCore port = travelCore.getTravelCorePort();
        String allFlights = port.getAllFlights();
        ListOfFlights list = unmarshal(allFlights);
        Iterator<Flight> it = list.getFlight().iterator();
        while (it.hasNext()) {
            Flight flight = it.next();
            Fare cost = flight.getFare();
            Fare newFare = convertFare(cost, currency);
            flight.setFare(newFare);
        }
        return marshal(list);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "makeBooking")
    public String makeBooking(@WebParam(name = "flightCode") String flightCode, @WebParam(name = "passengerName") String passengerName, @WebParam(name = "noOfSeats") String noOfSeats) {
        //TODO write your implementation code here:
        net.robertshippey.travelagency.core.reference.TravelCore port = travelCore.getTravelCorePort();
        String bookingResult = port.makeBooking(flightCode, passengerName, noOfSeats);
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
        net.robertshippey.travelagency.core.reference.TravelCore port = travelCore.getTravelCorePort();
        String searchResults = port.searchFlights(origin, desdination, date, directFlight);
        ListOfFlights list = unmarshal(searchResults);

        Iterator<Flight> it = list.getFlight().iterator();
        while (it.hasNext()) {
            Flight flight = it.next();
            Fare fare = flight.getFare();
            Fare newFare = convertFare(fare, currency);
            flight.setFare(newFare);
        }
        return marshal(list);
    }

    private Fare convertFare(Fare original, String desired) {
        CurrencyConversionWS port = currencyConverter.getCurrencyConversionWSPort();
        String originalCurrency = original.getCurrency();
        float originalValue = original.getAmount();
        double rate = port.getConversionRate(originalCurrency, desired);
        if (rate == -1) {
            return original;
        }
        float newValue = (float) (rate * originalValue);
        Fare fare = new Fare();
        fare.setAmount(newValue);
        fare.setCurrency(desired);
        return fare;
    }

    @WebMethod(operationName = "getCurrencyCodes")
    public List<String> getCurrencyCodes() {
        taha.currencyconversion.CurrencyConversionWS port = currencyConverter.getCurrencyConversionWSPort();
        return port.getCurrencyCodes();
    }

    private String marshal(ListOfFlights list) {
        try {
            StringWriter xml = new StringWriter();
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(list.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(list, xml);
            return xml.toString();
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            return "Error!"; //NOI18N
        }
    }

    private ListOfFlights unmarshal(String xml) {
        StringReader sr = new StringReader(xml);
        ListOfFlights list = new ListOfFlights();
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(list.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            list = (ListOfFlights) unmarshaller.unmarshal(sr); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
        }
        return list;
    }
}
