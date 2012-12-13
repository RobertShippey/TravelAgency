/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.core;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import net.robertshippey.travelagency.data.Flight;
import net.robertshippey.travelagency.data.ListOfFlights;
import net.robertshippey.travelagency.data.Passenger;

/**
 *
 * @author n0305434
 */
@WebService(serviceName = "TravelCore")
public class TravelCore {

    private final ListOfFlights listOfFlights = Data.getListOfFlights();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "makeBooking")
    public String makeBooking(@WebParam(name = "flightCode") String flightCode, @WebParam(name = "passengerName") String passengerName, @WebParam(name = "noOfSeats") String noOfSeats) {
        int seatsToBook = Integer.parseInt(noOfSeats);
        if(seatsToBook < 1){
            return "Can't book less than 1 seat.";
        }
        List<Flight> flights = listOfFlights.getFlight();
        Iterator<Flight> it;
        synchronized (flights) {
            it = flights.iterator();
        }
        while (it.hasNext()) {
            Flight flight = it.next();
            synchronized (flight) {
                if (flight.getFlightCode().equals(flightCode)) {
                    for (int x = 0; x < seatsToBook; x++) {
                        int avSeats = flight.getAvailableSeats();
                        Passenger p = new Passenger();
                        p.setName(passengerName);
                        p.setSeatNumber(avSeats);
                        flight.getPassenger().add(p);
                        flight.setAvailableSeats(avSeats - 1);
                    }
                    Data.unload();
                    ListOfFlights booked = new ListOfFlights();
                    booked.getFlight().add(flight);
                    return marshal(booked);
                }
            }
        }
        //TODO write your implementation code here:
        return "Error!";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "searchFlights")
    public String searchFlights(@WebParam(name = "origin") String origin,
            @WebParam(name = "destination") String destination,
            @WebParam(name = "date") String date,
            @WebParam(name = "directFlight") String directFlight) {
        //TODO write your implementation code here:
        
        //Ensureing not null
        String blank = "";
        if(origin == null){ origin = blank; }
        if(destination == null){ destination = blank;}
        if(date == null){ date = blank;}
        if(directFlight == null || (!directFlight.equalsIgnoreCase("yes") && !directFlight.equalsIgnoreCase("no"))){ date = blank;}
        
        ListOfFlights list = new ListOfFlights();
        List<Flight> flights = listOfFlights.getFlight();
        Iterator<Flight> it;
        synchronized (flights) {
            it = flights.iterator();
        }
        while (it.hasNext()) {
            Flight flight = it.next();
            synchronized (flight) {
                if (flight.getOriginCity().equalsIgnoreCase(origin)
                        || flight.getDestinationCity().equalsIgnoreCase(destination)
                        || flight.getDepartureDate().equals(date)
                        || (directFlight.equalsIgnoreCase("yes") && flight.getNumberOfConnections() == 1)
                        || (directFlight.equalsIgnoreCase("no") && flight.getNumberOfConnections() > 1)) {
                    list.getFlight().add(flight);
                }
            }
        }

        return marshal(list);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllFlights")
    public String getAllFlights() {
        synchronized (listOfFlights) {
            return marshal(listOfFlights);
        }
    }
    
    private String marshal (ListOfFlights list){
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
}
