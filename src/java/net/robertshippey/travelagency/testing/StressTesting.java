/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.testing;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.LinkedList;

/**
 *
 * @author Robert
 */
public class StressTesting {

    public static void main(String args[]) {

        System.out.println("One user, get all flights (x10)");
        long[] time = new long[10];
        for (int x = 0; x < 10; x++) {
            long start = Calendar.getInstance().getTimeInMillis();
            getAllFlights("GBP");
            long finish = Calendar.getInstance().getTimeInMillis();
            time[x] = finish - start;
        }
        long total = 0;
        for (int x = 0; x < time.length; x++) {
            total += time[x];
        }
        long average = total / time.length;
        System.out.println(average);

        final LinkedList list = new LinkedList();
        
        Thread[] connections = new Thread[10];
        for (int x = 0; x < connections.length; x++) {
            connections[x] = new Thread() {
                @Override
                public void run() {
                    long[] time = new long[10];
                    for (int x = 0; x < 10; x++) {
                        long start = Calendar.getInstance().getTimeInMillis();
                        getAllFlights("GBP");
                        long finish = Calendar.getInstance().getTimeInMillis();
                        time[x] = finish - start;
                    }
                    long total = 0;
                    for (int x = 0; x < time.length; x++) {
                        total += time[x];
                    }
                    long average = total / time.length;
                    list.add(average);
                }
            };
        }
        
        for(int x=0;x<connections.length;x++){
            connections[x].start();
        }
        
        boolean done = false;
        while(!done){
            boolean mightBeDone = false;
            for(int x=0;x<connections.length;x++){
                if(!connections[x].isAlive()){
                    mightBeDone = true;
                } else {
                    mightBeDone = false;
                }
            }
            if(mightBeDone){
                done = true;
            }
        }
        String[] resultsStr = (String[]) list.toArray(new String[0]);
        long multipleTotal = 0;
        for(int x=0;x<resultsStr.length;x++){
            multipleTotal += Long.parseLong(resultsStr[x]);
        }
        long multipleAverage = multipleTotal / resultsStr.length;
        System.out.println(multipleAverage);

    }

    private static String getAllFlights(java.lang.String currency) {
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service service = new net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service();
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService port = service.getTravelAgencyWebServicePort();
        return port.getAllFlights(currency);
    }
}
