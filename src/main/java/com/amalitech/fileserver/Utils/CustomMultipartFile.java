package com.amalitech.fileserver.Utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomMultipartFile implements MultipartFile {
    private final byte[] fileData;
    private final String fileName;

    public CustomMultipartFile(byte[] fileData, String fileName) {
        this.fileData = fileData;
        this.fileName = fileName;
    }

    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getOriginalFilename() {
        return fileName;
    }

    @Override
    public String getContentType() {
        // Set the appropriate content type based on your requirement
        return "application/octet-stream";
    }

    @Override
    public boolean isEmpty() {
        return fileData.length == 0;
    }

    @Override
    public long getSize() {
        return fileData.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return fileData;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileData);
    }

    @Override
    public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
        // Implement this method if you need to transfer the file to a local destination
        throw new UnsupportedOperationException("Transfer to file not supported");
    }
}
