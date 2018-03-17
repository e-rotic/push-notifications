package com.erotic.pushnotifications.fcm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author SkyCode
 */
@Component
public class FcmServer {

    @Value("${app.apikey}")
    private String API_KEY;
    private String COLLAPSE_KEY = "dmaria-message";
    private int RETRIES = 10;
    private String ICON = "ic_notification";
    private int TIME_TO_LIVE = 600;

    public void sendMessage(String to, Message message) throws IOException {
        Sender sender = new Sender(API_KEY);
        Result result = sender.send(message, to, RETRIES);

        System.out.println(result.toString());
    }

    public void sendMessage(List<String> to, Message message) throws IOException {
        Sender sender = new Sender(API_KEY);
        MulticastResult result = sender.send(message, to, RETRIES);
    }

    public void sendMessage(String to, Message message, int retries) throws IOException {
        Sender sender = new Sender(API_KEY);
        Result result = sender.send(message, to, retries);
    }

    public void sendMessage(List<String> to, Message message, int retries) throws IOException {
        Sender sender = new Sender(API_KEY);
        MulticastResult result = sender.send(message, to, retries);
    }

    public Notification buildNotification(String title, String body) {
        Notification.Builder builder = new Notification.Builder(ICON);
        builder.title(title);
        builder.body(body);

        return builder.build();
    }

    private Notification buildNotification(String title, String body, String icon) {
        Notification.Builder builder = new Notification.Builder(icon);
        builder.title(title);
        builder.body(body);

        return builder.build();
    }

    public Message buildMessage(String title, String body, HashMap<String, String> data, String collapseKey) {
        Notification notification = buildNotification(title, body);
        Message.Builder builder = new Message.Builder();
        builder.notification(notification);
        builder.collapseKey(collapseKey);
        builder.contentAvailable(Boolean.TRUE);
        builder.timeToLive(TIME_TO_LIVE);

        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            builder.addData(pair.getKey().toString(), pair.getValue().toString());
        }

        return builder.build();
    }

    public Message buildMessage(String title, String body, HashMap<String, String> data) {
        Notification notification = buildNotification(title, body);
        Message.Builder builder = new Message.Builder();
        builder.notification(notification);
        builder.collapseKey(COLLAPSE_KEY);
        builder.contentAvailable(Boolean.TRUE);
        builder.timeToLive(TIME_TO_LIVE);

        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            builder.addData(pair.getKey().toString(), pair.getValue().toString());
        }

        return builder.build();
    }

    public Message buildMessage(String title, String body, HashMap<String, String> data, int timeToLive) {
        Notification notification = buildNotification(title, body);
        Message.Builder builder = new Message.Builder();
        builder.notification(notification);
        builder.collapseKey(COLLAPSE_KEY);
        builder.contentAvailable(Boolean.TRUE);
        builder.timeToLive(timeToLive);

        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            builder.addData(pair.getKey().toString(), pair.getValue().toString());
        }

        return builder.build();
    }

    public Message buildMessage(String title, String body, HashMap<String, String> data, int timeToLive,
                                       String collapseKey) {
        Notification notification = buildNotification(title, body);
        Message.Builder builder = new Message.Builder();
        builder.notification(notification);
        builder.collapseKey(collapseKey);
        builder.contentAvailable(Boolean.TRUE);
        builder.timeToLive(timeToLive);

        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            builder.addData(pair.getKey().toString(), pair.getValue().toString());
        }

        return builder.build();
    }
}
