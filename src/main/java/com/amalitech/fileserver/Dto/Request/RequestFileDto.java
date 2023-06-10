package com.amalitech.fileserver.Dto.Request;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RequestFileDto {
    private String title;
    private String description;
}
