/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TableHockeyClient;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adnanfahed
 */
public final class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();

        pnl_connected.setVisible(false);
        requestMatchBtnVisibility();
        
        portField.setText("5003");
        ipField.setText("0.0.0.0");
    }

    static Client client = null;

    public void connect() {

        ClientCallbacks callbacks = new ClientCallbacks() {
            @Override
            public void setRequestedMatchStatus(boolean b) {
                String txt;

                if (b) {
                    txt = "Requested Successfully!!";
                } else {
                    txt = "Request Failed!";
                }

                lbl_request_match_stat.setText(txt);
            }

            @Override
            public void disconnect() {
                lbl_connection_state.setText("Disconnected");
                pnl_connected.setVisible(false);
            }

            @Override
            public void startGame(Socket gameSocket) {
                GameScreen scrn = new GameScreen(gameSocket);
                
                scrn.setVisible(true);
            }
        };

        try {

            Socket socket = new Socket(getInputIp(), getInputPort());
            client = new Client(socket, callbacks);
            lbl_connection_state.setText(
                    "connected to " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
            pnl_connected.setVisible(true);
        } catch (IOException ex) {
            lbl_connection_state.setText("an error occured..");
            pnl_connected.setVisible(false);
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    public void requestMatch() {
        String nm = getPlayerName();
        client.requestMatch(nm);
    }

    public String getInputIp() {
        return ipField.getText();
    }

    public int getInputPort() {
        return Integer.valueOf(portField.getText());
    }

    String getPlayerName() {
        return fld_player_name.getText();
    }

    void requestMatchBtnVisibility() {
        String nm = getPlayerName();

        btn_request_match.setEnabled(nm != null && !nm.isEmpty());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_main = new javax.swing.JPanel();
        pnl_connection = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ipField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        portField = new javax.swing.JTextField();
        btn_connect = new javax.swing.JButton();
        lbl_connection_state = new javax.swing.JLabel();
        pnl_connected = new javax.swing.JPanel();
        pnl_request_match = new javax.swing.JPanel();
        btn_request_match = new javax.swing.JButton();
        lbl_request_match_stat = new javax.swing.JLabel();
        fld_player_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("IP");

        ipField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipFieldActionPerformed(evt);
            }
        });
        ipField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ipFieldKeyTyped(evt);
            }
        });
        ipField.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                ipFieldVetoableChange(evt);
            }
        });

        jLabel2.setText("Port");

        portField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portFieldActionPerformed(evt);
            }
        });
        portField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                portFieldKeyTyped(evt);
            }
        });

        btn_connect.setText("Connect");
        btn_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connectActionPerformed(evt);
            }
        });

        lbl_connection_state.setText("No Connection!!");

        javax.swing.GroupLayout pnl_connectionLayout = new javax.swing.GroupLayout(pnl_connection);
        pnl_connection.setLayout(pnl_connectionLayout);
        pnl_connectionLayout.setHorizontalGroup(
            pnl_connectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_connectionLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnl_connectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_connect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnl_connectionLayout.createSequentialGroup()
                        .addGroup(pnl_connectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_connectionLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_connection_state, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 61, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl_connectionLayout.setVerticalGroup(
            pnl_connectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_connectionLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnl_connectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_connect)
                .addGap(18, 18, 18)
                .addComponent(lbl_connection_state)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btn_request_match.setText("Request Match");
        btn_request_match.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_request_matchActionPerformed(evt);
            }
        });

        lbl_request_match_stat.setText("Not Requested Yet!!");

        fld_player_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fld_player_nameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fld_player_nameKeyTyped(evt);
            }
        });

        jLabel3.setText("Name:");

        javax.swing.GroupLayout pnl_request_matchLayout = new javax.swing.GroupLayout(pnl_request_match);
        pnl_request_match.setLayout(pnl_request_matchLayout);
        pnl_request_matchLayout.setHorizontalGroup(
            pnl_request_matchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_request_matchLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnl_request_matchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnl_request_matchLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fld_player_name, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_request_match, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_request_match_stat, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        pnl_request_matchLayout.setVerticalGroup(
            pnl_request_matchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_request_matchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_request_match_stat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_request_matchLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnl_request_matchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fld_player_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_request_match))
        );

        javax.swing.GroupLayout pnl_connectedLayout = new javax.swing.GroupLayout(pnl_connected);
        pnl_connected.setLayout(pnl_connectedLayout);
        pnl_connectedLayout.setHorizontalGroup(
            pnl_connectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_request_match, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_connectedLayout.setVerticalGroup(
            pnl_connectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_connectedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_request_match, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(206, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_mainLayout = new javax.swing.GroupLayout(pnl_main);
        pnl_main.setLayout(pnl_mainLayout);
        pnl_mainLayout.setHorizontalGroup(
            pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_connection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnl_connected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_mainLayout.setVerticalGroup(
            pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_mainLayout.createSequentialGroup()
                .addComponent(pnl_connection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_connected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_main);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connectActionPerformed
        // TODO add your handling code here:
        connect();
    }//GEN-LAST:event_btn_connectActionPerformed

    private void ipFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipFieldActionPerformed

    private void portFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portFieldActionPerformed

    private void ipFieldVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_ipFieldVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_ipFieldVetoableChange

    private void ipFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ipFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ipFieldKeyTyped

    private void portFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_portFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_portFieldKeyTyped

    private void btn_request_matchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_request_matchActionPerformed
        // TODO add your handling code here:
        requestMatch();
    }//GEN-LAST:event_btn_request_matchActionPerformed

    private void fld_player_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fld_player_nameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_player_nameKeyTyped

    private void fld_player_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fld_player_nameKeyReleased
        // TODO add your handling code here:
        requestMatchBtnVisibility();
    }//GEN-LAST:event_fld_player_nameKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_connect;
    private javax.swing.JButton btn_request_match;
    private javax.swing.JTextField fld_player_name;
    private javax.swing.JTextField ipField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbl_connection_state;
    private javax.swing.JLabel lbl_request_match_stat;
    private javax.swing.JPanel pnl_connected;
    private javax.swing.JPanel pnl_connection;
    private javax.swing.JPanel pnl_main;
    private javax.swing.JPanel pnl_request_match;
    private javax.swing.JTextField portField;
    // End of variables declaration//GEN-END:variables
}
