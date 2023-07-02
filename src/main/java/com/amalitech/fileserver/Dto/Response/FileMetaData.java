package com.amalitech.fileserver.Dto.Response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FileMetaData {
    String name;
    int numberOfDownloads;
    int numberOfSentEmails;
}
