package com.amalitech.fileserver.Services.Impl;

import com.amalitech.fileserver.Dto.Response.FileMetaData;
import com.amalitech.fileserver.Models.File;
import com.amalitech.fileserver.Repositories.FileRepository;
import com.amalitech.fileserver.Services.Interfaces.FileService;
import com.amalitech.fileserver.Utils.FileConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private  final FileRepository fileRepository;
    private final FileConverter fileConverter;

    @Override
    public void saveFileMetaData(String fileName) {
        fileRepository.save(new File(fileName, 0, 0));
    }

    @Override
    public List<FileMetaData> getAllFilesMetaData() {
        return fileRepository.findAll()
                .stream()
                .map(fileConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public boolean increaseDownloadCount(String fileName) {
        Optional<File> file = fileRepository.findByName(fileName);

        if(file.isPresent()) {
            var fileEntity = file.get();
            fileEntity.setNumberOfDownloads(fileEntity.getNumberOfDownloads() + 1);
            fileRepository.save(fileEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean increaseEmailCount(String fileName) {
        Optional<File> file = fileRepository.findByName(fileName);

        if(file.isPresent()) {
            var fileEntity = file.get();
            fileEntity.setNumberOfSentEmails(fileEntity.getNumberOfSentEmails() + 1);
            fileRepository.save(fileEntity);
            return true;
        }
        return false;
    }
}
