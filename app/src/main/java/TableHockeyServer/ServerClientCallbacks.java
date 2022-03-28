/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockeyServer;

/**
 *
 * @author adnanfahed
 */
public interface ServerClientCallbacks {
    void requestQueue(ServerClient client);
    void removeClient(ServerClient client);
}
