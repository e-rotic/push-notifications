package com.erotic.pushnotifications;

import com.erotic.pushnotifications.fcm.FcmNotifier;
import com.erotic.pushnotifications.fcm2.FCMNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SkyCode
 */
@RestController
@RequestMapping("/api")
public class SampleController {

    @Autowired
    private FcmNotifier notifier;

    @Autowired
    private FCMNotifier notifierNew;

    @Value("${app.topic}")
    private String topic;

    @GetMapping("/notify")
    public ResponseEntity<String> notify(@RequestParam(required = false, defaultValue = "Hello") String title,
                                         @RequestParam(required = false, defaultValue = "Hello World!") String body) {
        notifier.send(topic, title, body);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/notify/new")
    public ResponseEntity<String> notifyNew(@RequestParam(required = false, defaultValue = "Hello") String title,
                                         @RequestParam(required = false, defaultValue = "Hello World!") String body) {
        return new ResponseEntity<>(notifierNew.send(title, body), HttpStatus.OK);
    }
}
