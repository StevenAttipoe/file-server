package com.amalitech.fileserver.Services.Interfaces;

import com.amalitech.fileserver.Dto.Response.FileMetaData;

import java.util.List;

public interface FileService {
    public void saveFileMetaData(String fileName);
    public List<FileMetaData> getAllFilesMetaData();
    public boolean increaseDownloadCount(String fileName);
    public boolean increaseEmailCount(String fileName);

}
