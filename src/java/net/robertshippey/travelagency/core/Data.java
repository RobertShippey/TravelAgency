/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.core;

import net.robertshippey.travelagency.data.ListOfFlights;

/**
 *
 * @author Robert
 */
public class Data {

    private static boolean loaded = false;
    private static final java.io.File xmlFile = new java.io.File("flights.xml");
    private static ListOfFlights listOfFlights = new ListOfFlights();

    public static void loadData() {
        if (!loaded) {
            load();
        }
    }

    public static ListOfFlights getListOfFlights() {
        loadData();
        return listOfFlights;
    }

    private static void load() {
        System.out.println("current dir = " + System.getProperty("user.dir"));
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(listOfFlights.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            listOfFlights = (ListOfFlights) unmarshaller.unmarshal(xmlFile); //NOI18N
            loaded = true;
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }

    }

    public static void unload() {
        new Thread() {
            public void run() {
                synchronized (Data.listOfFlights) {
                    try {
                        javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(Data.listOfFlights.getClass().getPackage().getName());
                        javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
                        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
                        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                        marshaller.marshal(Data.listOfFlights, Data.xmlFile);
                    } catch (javax.xml.bind.JAXBException ex) {
                        // XXXTODO Handle exception
                        java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
                    }
                }
            }
        }.start();
        
    }
}
