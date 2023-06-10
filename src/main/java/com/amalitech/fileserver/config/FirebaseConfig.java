//package com.amalitech.fileserver.config;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.storage.StorageOptions;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//@Configuration
//public class FirebaseConfig {
//
//    @Value("${app.servicePathKey}")
//    String SERVICE_KEY_PATH;
//
//    @PostConstruct
//    public void initialize() throws IOException {
//        FileInputStream serviceAccount =
//                new FileInputStream(SERVICE_KEY_PATH);
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .build();
//
//        FirebaseApp.initializeApp(options);
//    }
//
//    public StorageOptions getStorageOptions() throws IOException {
//        FileInputStream serviceAccount =
//                new FileInputStream(SERVICE_KEY_PATH);
//
//        return StorageOptions.newBuilder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .build();
//    }
//
//}
