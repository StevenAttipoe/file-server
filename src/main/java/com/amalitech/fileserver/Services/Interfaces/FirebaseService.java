package com.amalitech.fileserver.Services.Interfaces;

import com.amalitech.fileserver.Dto.Response.ResponseFileDto;
import com.amalitech.fileserver.Utils.PaginationResult;
import com.google.cloud.storage.Blob;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FirebaseService {
    public boolean uploadFile(MultipartFile file, String title , String description);

    public List<ResponseFileDto> getAllFiles(int page, int pageSize);

    public PaginationResult<List<ResponseFileDto>> getBlobsByPage(int pageNumber, int pageSize);

    public Blob getFile(String fileName);

    MultipartFile downloadFileFromFirebase(String fileName) throws IOException;
}
