package com.amalitech.fileserver.Controllers;

import com.amalitech.fileserver.Dto.Response.FileMetaData;
import com.amalitech.fileserver.Dto.Response.ResponseFileDto;
import com.amalitech.fileserver.Services.Interfaces.FileService;
import com.amalitech.fileserver.Services.Interfaces.FirebaseService;
import com.amalitech.fileserver.Utils.PaginationResult;
import com.amalitech.fileserver.Utils.RequestResponse;
import com.google.cloud.storage.Blob;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@RequestMapping("api/v1/files")
@AllArgsConstructor
@RestController
public class FilesController {
    private final FirebaseService firebaseService;
    private final FileService fileService;

    @GetMapping("/get")
    public ResponseEntity<InputStreamResource> getFile(@RequestParam String fileName) {
        Blob blob = firebaseService.getFile(fileName);

        if (blob == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] fileBytes = blob.getContent();

        MediaType mediaType = MediaType.parseMediaType(blob.getContentType());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("inline", fileName);
        headers.setContentType(mediaType);

        InputStream inputStream = new ByteArrayInputStream(fileBytes);
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(mediaType)
                .body(inputStreamResource);
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description){
        if (file.isEmpty()) {
            return RequestResponse.error(HttpStatus.BAD_REQUEST, "Please select a file to upload.");
        }
        firebaseService.uploadFile(file, title, description);
        return RequestResponse.success("File uploaded successfully. File Name: " + file.getOriginalFilename());
    }

    @GetMapping("/get/all")
    public ResponseEntity<PaginationResult<List<ResponseFileDto>>> getFiles(@RequestParam(defaultValue = "1") int page,
                                                                            @RequestParam(defaultValue = "8") int pageSize){
        return ResponseEntity.ok(firebaseService.getBlobsByPage(page, pageSize));
    }

    @GetMapping("/get/metadata/all")
    public ResponseEntity<List<FileMetaData>> getFilesMetaData(){
        return ResponseEntity.ok(fileService.getAllFilesMetaData());
    }

    @PutMapping("/update/download-count")
    public ResponseEntity updateFileDownloadCount(@RequestParam("fileName") String fileName) {
        if (fileService.increaseDownloadCount(fileName)){
            return RequestResponse.success(true);
        }
        return RequestResponse.error(HttpStatus.NOT_FOUND, "File does not exist");
    }

    @PutMapping("/update/email-count")
    public ResponseEntity updateFileEmailCount(@RequestParam("fileName") String fileName) {
        if (fileService.increaseEmailCount(fileName)){
            return RequestResponse.success(true);
        }
        return RequestResponse.error(HttpStatus.NOT_FOUND, "File does not exist");
    }
}
