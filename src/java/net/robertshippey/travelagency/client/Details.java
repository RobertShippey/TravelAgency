/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.robertshippey.travelagency.client;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.xml.ws.WebServiceException;
import net.robertshippey.travelagency.data.Flight;
import net.robertshippey.travelagency.data.Passenger;

/**
 *
 * @author Robert
 */
public class Details extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private Flight theFlight;
    private TableModel passengerModel;

    /**
     * Creates new form Details
     */
    public Details(Flight f) {
        theFlight = f;
        passengerModel = new PassengerTableModel(theFlight.getPassenger());
        initComponents();

        airline.setText(theFlight.getAirline());
        availableSeats.setText(String.valueOf(theFlight.getAvailableSeats()));
        departureDate.setText(theFlight.getDepartureDate());
        destinationCity.setText(theFlight.getDestinationCity());
        String monies = String.format("%.2f %s", theFlight.getFare().getAmount(), theFlight.getFare().getCurrency());
        fare.setText(monies);
        flightCode.setText(theFlight.getFlightCode());
        noOfConnections.setText(String.valueOf(theFlight.getNumberOfConnections()));
        originCity.setText(theFlight.getOriginCity());

    }

    private TableModel getTableModel() {
        return passengerModel;
    }

    /**
     * This method is called from within the constructor to initialize
     * the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        flightCode = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        originCity = new javax.swing.JLabel();
        destinationCity = new javax.swing.JLabel();
        airline = new javax.swing.JLabel();
        departureDate = new javax.swing.JLabel();
        availableSeats = new javax.swing.JLabel();
        noOfConnections = new javax.swing.JLabel();
        fare = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        altDirButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Flight Code:");

        flightCode.setText("blank");

        jLabel3.setText("Origin City:");

        jLabel4.setText("Destination City:");

        jLabel5.setText("Airline:");

        jLabel6.setText("Departure Date:");

        jLabel7.setText("Available Seats");

        jLabel8.setText("Number of Connections:");

        jLabel9.setText("Fare:");

        originCity.setText("jLabel10");

        destinationCity.setText("jLabel11");

        airline.setText("jLabel12");

        departureDate.setText("jLabel13");

        availableSeats.setText("jLabel14");

        noOfConnections.setText("jLabel15");

        fare.setText("jLabel16");

        nameText.setText("Name");
        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });

        jButton1.setText("Book Seat");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Make Booking");

        jTable1.setModel(getTableModel());
        jScrollPane1.setViewportView(jTable1);

        jLabel10.setText("Passengers:");

        altDirButton.setText("Alt. directions");
        altDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altDirButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jLabel10)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(flightCode)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jLabel2))
                            .add(layout.createSequentialGroup()
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(originCity)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(nameText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(jLabel4)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(destinationCity)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jButton1)))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel5)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(airline))
                            .add(layout.createSequentialGroup()
                                .add(jLabel6)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(departureDate)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(altDirButton))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel7)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(availableSeats))
                            .add(layout.createSequentialGroup()
                                .add(jLabel8)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(noOfConnections))
                            .add(layout.createSequentialGroup()
                                .add(jLabel9)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(fare))
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 375, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(flightCode)
                            .add(jLabel2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(originCity)
                            .add(nameText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(destinationCity)
                            .add(jButton1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel5)
                            .add(airline))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel6)
                            .add(departureDate)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, altDirButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(availableSeats))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(noOfConnections))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(fare))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel10)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 168, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String name = nameText.getText();
            String number = String.valueOf(1);

            String result = makeBooking(theFlight.getFlightCode(), name, number);
            boolean worked = Boolean.getBoolean(result);
            String message;
            if (worked) {
                message = number + " seat(s) booked for " + name;
            } else {
                message = "Couln't book seat(s)";
            }
            JOptionPane.showMessageDialog(null, message);
        } catch (WebServiceException wse) {
            JOptionPane.showMessageDialog(this, "Couldn't contact web service, no bookings made.");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void altDirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altDirButtonActionPerformed
        String origin = theFlight.getOriginCity();
        String destination = theFlight.getDestinationCity();
        new Directions(origin, destination).setVisible(true);
    }//GEN-LAST:event_altDirButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel airline;
    private javax.swing.JButton altDirButton;
    private javax.swing.JLabel availableSeats;
    private javax.swing.JLabel departureDate;
    private javax.swing.JLabel destinationCity;
    private javax.swing.JLabel fare;
    private javax.swing.JLabel flightCode;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nameText;
    private javax.swing.JLabel noOfConnections;
    private javax.swing.JLabel originCity;
    // End of variables declaration//GEN-END:variables

    private static String makeBooking(java.lang.String flightCode, java.lang.String passengerName, java.lang.String noOfSeats) {
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service service = new net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService_Service();
        net.robertshippey.travelagency.webservice.reference.TravelAgencyWebService port = service.getTravelAgencyWebServicePort();
        return port.makeBooking(flightCode, passengerName, noOfSeats);
    }

    private class PassengerTableModel implements TableModel {

        private Passenger[] passengers;

        public PassengerTableModel(List list) {
            passengers = (Passenger[]) list.toArray(new Passenger[0]);
        }

        @Override
        public int getRowCount() {
            return passengers.length;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int i) {
            if (i == 0) {
                return "Name";
            } else if (i == 1) {
                return "Seat No";
            } else {
                return "Error";
            }
        }

        @Override
        public Class<?> getColumnClass(int i) {
            return String.class;
        }

        @Override
        public boolean isCellEditable(int i, int i1) {
            return false;
        }

        @Override
        public Object getValueAt(int i, int i1) {
            if (i1 == 0) {
                return passengers[i].getName();
            } else if (i1 == 1) {
                return passengers[i].getSeatNumber();
            } else {
                return "Error";
            }
        }

        @Override
        public void setValueAt(Object o, int i, int i1) {
        }

        @Override
        public void addTableModelListener(TableModelListener tl) {
        }

        @Override
        public void removeTableModelListener(TableModelListener tl) {
        }
    }
}
