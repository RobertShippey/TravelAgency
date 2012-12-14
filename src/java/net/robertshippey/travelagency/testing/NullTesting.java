/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.testing;

/**
 *
 * @author Robert
 */
public class NullTesting {

    public static void main(String[] args) {
        try {
            System.out.println("getAllFlights");
            System.out.println(getAllFlights(null));
            System.out.println();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            System.out.println("makeBooking");
            System.out.println(makeBooking(null, null, null));
            System.out.println();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            System.out.println("searchFlights");
            System.out.println(searchFlights(null, null, null, null, null));
            System.out.println();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static String getAllFlights(java.lang.String currency) {
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service service = new net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service();
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService port = service.getTravelAgencyWebServicePort();
        return port.getAllFlights(currency);
    }

    private static java.util.List<java.lang.String> getCurrencyCodes() {
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service service = new net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service();
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService port = service.getTravelAgencyWebServicePort();
        return port.getCurrencyCodes();
    }

    private static String makeBooking(java.lang.String flightCode, java.lang.String passengerName, java.lang.String noOfSeats) {
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service service = new net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service();
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService port = service.getTravelAgencyWebServicePort();
        return port.makeBooking(flightCode, passengerName, noOfSeats);
    }

    private static String searchFlights(java.lang.String origin, java.lang.String desdination, java.lang.String date, java.lang.String directFlight, java.lang.String currency) {
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service service = new net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service();
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService port = service.getTravelAgencyWebServicePort();
        return port.searchFlights(origin, desdination, date, directFlight, currency);
    }
}
