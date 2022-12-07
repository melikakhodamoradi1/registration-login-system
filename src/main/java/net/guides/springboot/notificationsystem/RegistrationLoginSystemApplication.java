package net.guides.springboot.notificationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;


@ComponentScan("net")
@SpringBootApplication()
@EnableScheduling
public class RegistrationLoginSystemApplication {

    public static void main(String[] args) throws IOException {
      /*  ClassPathResource classPathResource = new ClassPathResource("serviceAccountKey.json");
        File file = classPathResource.getFile();
        FileInputStream serviceAccount =
                new FileInputStream(file.getAbsolutePath());


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://melika-project-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);*/


        SpringApplication.run(RegistrationLoginSystemApplication.class, args);
    }
}

