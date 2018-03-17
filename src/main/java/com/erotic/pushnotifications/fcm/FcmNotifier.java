package com.erotic.pushnotifications.fcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author SkyCode
 */
@Service
public class FcmNotifier {

    @Autowired
    private FcmServer fcmServer;

    private final String TYPE = "type";
    private final String TITLE = "title";
    private final String BODY = "body";

    public void send(String to, String title, String msg) {
        HashMap<String, String> data = new HashMap<>();
        data.put(TITLE, title);
        data.put(BODY, msg);

        Message message = fcmServer.buildMessage(data.get(TITLE), data.get(BODY), data);
        try {
            fcmServer.sendMessage("/topics/" + to, message);
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public void send(List<String> to, String title, String msg) {
        HashMap<String, String> data = new HashMap<>();
        data.put(TITLE, title);
        data.put(BODY, msg);

        Message message = fcmServer.buildMessage(data.get(TITLE), data.get(BODY), data);

        try {
            if (to.size() > 0) {
                fcmServer.sendMessage(to, message);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
