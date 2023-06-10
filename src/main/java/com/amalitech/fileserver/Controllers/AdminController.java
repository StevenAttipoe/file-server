package com.amalitech.fileserver.Controllers;

import com.amalitech.fileserver.Dto.Request.RequestFileDto;
import com.amalitech.fileserver.Services.Interfaces.FirebaseService;
import com.amalitech.fileserver.Utils.RequestResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/v1/admin")
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    private final FirebaseService firebaseService;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description){
        if (file.isEmpty()) {
            return RequestResponse.error(HttpStatus.BAD_REQUEST, "Please select a file to upload.");
        }
        firebaseService.uploadFile(file, title, description);
        return RequestResponse.success("File uploaded successfully. File Name: " + file.getOriginalFilename());
    }
}
