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
    public boolean makeBooking(@WebParam(name = "flightCode") String flightCode, @WebParam(name = "passengerName") String passengerName, @WebParam(name = "noOfSeats") String noOfSeats) {
        int seatsToBook = Integer.parseInt(noOfSeats);
        if(seatsToBook < 1){
            return false;
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
                    return true;
                }
            }
        }
        //TODO write your implementation code here:
        return false;
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
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllFlights")
    public String getAllFlights() {
        StringWriter xml = new StringWriter();
        synchronized (listOfFlights) {
            try {
                javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(listOfFlights.getClass().getPackage().getName());
                javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(listOfFlights, xml);
            } catch (javax.xml.bind.JAXBException ex) {
                // XXXTODO Handle exception
                java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
            }
        }
        return (xml.toString());
    }
}
