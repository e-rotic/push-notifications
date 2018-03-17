package com.erotic.pushnotifications.fcm2;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author SkyCode
 */
@Service
public class FCMNotifier {

    @Value("${app.topic}")
    private String topic;

    public String send(String title, String body) {
        try {
            Message message = Message.builder()
                    .setNotification(new Notification(title, body))
                    .setTopic(topic)
                    .build();
            String response = FirebaseMessaging.getInstance().sendAsync(message).get();
            return response;
        } catch (Exception ex) {
            return null;
        }
    }
}
