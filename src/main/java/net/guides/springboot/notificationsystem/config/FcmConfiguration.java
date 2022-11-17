package net.guides.springboot.notificationsystem.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@Configuration
@EnableAsync
public class FcmConfiguration {



    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("serviceAccountKey.json").getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .setDatabaseUrl("https://melika-project-default-rtdb.firebaseio.com")
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions , "my-app");
        return FirebaseMessaging.getInstance(app);
    }
}
