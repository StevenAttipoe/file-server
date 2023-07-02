package com.amalitech.fileserver.Utils;

import com.amalitech.fileserver.Dto.Response.FileMetaData;
import com.amalitech.fileserver.Models.File;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FileConverter implements Converter<File, FileMetaData> {
    @Override
    public FileMetaData convert(File source) {
        return FileMetaData.builder()
                .name(source.getName())
                .numberOfDownloads((source.getNumberOfDownloads()))
                .numberOfSentEmails(source.getNumberOfSentEmails())
                .build();
    }
}
