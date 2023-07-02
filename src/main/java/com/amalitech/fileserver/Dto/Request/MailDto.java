package com.amalitech.fileserver.Dto.Request;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MailDto {
    String recipientAddress;
    String fileName;
}
