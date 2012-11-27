/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.testing;

import java.io.File;
import java.util.List;
import net.robertshippey.travelagency.data.*;

/**
 *
 * @author n0305434
 */
public class Output {

    public static void main(String[] args) {
        //Create a new list container
        ListOfFlights availableFlights = new ListOfFlights();
        
        //Get the list from the container
        List<Flight> flights = availableFlights.getFlight();
        
        //Construct a new flight
        Flight f = new Flight();
        f.setAirline("EasyJet");
        f.setAvailableSeats(200);
        f.setDestinationCity("NewYork");
        f.setNumberOfConnections(1);
        f.setOriginCity("London");
        Fare fare = new Fare();
            fare.setAmount(500.00f);
            fare.setCurrency("GBP");
        f.setFare(fare);
        //create a new passenger
        Passenger person = new Passenger();
            //add details
            person.setName("John Smith");
            person.setSeatNumber(12);
            //get the passenger list and add new person
        f.getPassenger().add(person);
        person = new Passenger();
            person.setName("Andrew Danes");
            person.setSeatNumber(34);
        f.getPassenger().add(person);
        //add new flight to list
        flights.add(f);
        
        f = new Flight();
        f.setAirline("HighFlies");
        f.setAvailableSeats(200);
        f.setDestinationCity("Sydney");
        f.setNumberOfConnections(1);
        f.setOriginCity("Edinburgh");
        fare = new Fare();
            fare.setAmount(950.00f);
            fare.setCurrency("AUD");
        f.setFare(fare);
        
        flights.add(f);
        
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(availableFlights.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(availableFlights, new File("flights.xml"));
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
    }
}
