package com.amalitech.fileserver.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class RequestResponse {

    public static ResponseEntity<Object> success( Object object) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", object);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Map<String, Object>> success(String field, Object object) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put(field, object);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Object> error(HttpStatus status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }
}
