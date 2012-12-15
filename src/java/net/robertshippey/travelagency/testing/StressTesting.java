/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.testing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

/**
 *
 * @author Robert
 */
public class StressTesting {

    private static int n;
    private static BufferedWriter bw;

    public static void main(String args[]) throws IOException, InterruptedException {
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("results.csv"))));

        bw.write("method, number of concurrent connections, thread id, attempt number, time in miliseconds\r\n");
        bw.flush();

        for (n = 1; n < 10; n++) {
            WSThread[] connections = new WSThread[n];
            for (int c = 0; c < connections.length; c++) {
                connections[c] = new WSThread();
                connections[c].threadID = c;
            }

            for (int x = 0; x < connections.length; x++) {
                connections[x].start();
            }

            doneLoop:
            while (true) {
                for (int x = 0; x < connections.length; x++) {
                    if (!connections[x].done) {
                        continue doneLoop;
                    }
                }
                break;
            }
            connections = null;
            System.gc();
            Thread.sleep(5000);
        }

        bw.flush();
        bw.close();
        System.out.println("Done!");

    }

    private static String getAllFlights(java.lang.String currency) {
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service service = new net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service();
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService port = service.getTravelAgencyWebServicePort();
        return port.getAllFlights(currency);
    }

    private static class WSThread extends Thread {

        public int threadID;
        public boolean done = false;

        @Override
        public void run() {
            for (int x = 0; x < 10; x++) {
                long time = -1;
                try {
                    try {
                        long start = Calendar.getInstance().getTimeInMillis();
                        searchFlights("London", "Paris", "20/02/2013", "yes", "USD");
                        long finish = Calendar.getInstance().getTimeInMillis();
                        time = finish - start;
                    } catch (Exception e) {
                        bw.write("searchFlights," + n + "," + threadID + "," + x + ",failed\r\n");
                        bw.flush();
                        continue;
                    }

                    bw.write("searchFlights," + n + "," + threadID + "," + x + "," + time + "\r\n");
                    bw.flush();
                } catch (Exception ex) {
                    System.out.println("Couldn't output: " + "searchFlights," + n + "," + threadID + "," + x + "," + time + "\r\n");
                }
            }
            done = true;
        }
    }

    private static String searchFlights(java.lang.String origin, java.lang.String desdination, java.lang.String date, java.lang.String directFlight, java.lang.String currency) {
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service service = new net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service();
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService port = service.getTravelAgencyWebServicePort();
        return port.searchFlights(origin, desdination, date, directFlight, currency);
    }
}
