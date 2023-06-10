package com.amalitech.fileserver.Controllers;

import com.amalitech.fileserver.Dto.Response.ResponseFileDto;
import com.amalitech.fileserver.Services.Interfaces.FirebaseService;
import com.google.cloud.storage.Blob;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@RequestMapping("api/v1/files")
@AllArgsConstructor
@RestController
public class FilesController {
    private final FirebaseService firebaseService;

    @GetMapping("/get/all")
    public ResponseEntity<List<ResponseFileDto>> getFiles(@RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "11") int pageSize){
        return ResponseEntity.ok(firebaseService.getAllFiles(page, pageSize));
    }

    @GetMapping("/get")
    public ResponseEntity<InputStreamResource> getFile(@RequestParam String fileName) {
        Blob blob = firebaseService.getFile(fileName);

        if (blob == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] fileBytes = blob.getContent();

        // Set the appropriate content type based on the file's content
        MediaType mediaType = MediaType.parseMediaType(blob.getContentType());

        // Set the Content-Disposition header to inline, which tells the browser to display the file
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("inline", fileName);
        headers.setContentType(mediaType);

        // Create an InputStreamResource from the file bytes
        InputStream inputStream = new ByteArrayInputStream(fileBytes);
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        // Return the ResponseEntity with the InputStreamResource, headers, and OK status
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(mediaType)
                .body(inputStreamResource);
    }
}
