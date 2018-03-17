package com.erotic.pushnotifications.fcm2;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;

/**
 * @author SkyCode
 */
@Configuration
public class FCMConfig {

    @Bean
    public FirebaseApp config() throws Exception {
        FileInputStream serviceAccount = new FileInputStream("sample-c0146-firebase-adminsdk-bb110-8d63c4365d.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp def = FirebaseApp.initializeApp(options);
        return def;
    }
}
