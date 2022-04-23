/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockey;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author adnanfahed
 */
public class SocketMessage implements Serializable {

    public String title;
    public Object data;
    public Map params;
    public String status;

    public SocketMessage(String title, Map params, String status) {
        this.title = title;
        this.params = params;
        this.status = status;

    }

    public SocketMessage(String title, Map params) {
        this.title = title;
        this.params = params;
    }

    public SocketMessage(String title, String status) {
        this.title = title;
        this.status = status;
    }
    
    public SocketMessage(String title, Object data) {
        this.title = title;
        this.data = data;
    }

    public SocketMessage(String title) {
        this.title = title;
    }
    public SocketMessage(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return status.equals(StatusCodes.success);
    }
}
