/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockey;

import java.io.Serializable;

/**
 *
 * @author adnanfahed
 */
public class SocketMessage implements Serializable{

    public String title;
    public String status;

    public SocketMessage(String title, String status) {
        this.title = title;
        this.status = status;
    }

    public SocketMessage(String title) {
        this.title = title;
    }

    
   public boolean isSuccess() {
        return status.equals(StatusCodes.success);
    }
}
