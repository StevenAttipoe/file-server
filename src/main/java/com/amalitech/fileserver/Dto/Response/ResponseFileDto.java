package com.amalitech.fileserver.Dto.Response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ResponseFileDto {
    String name;
    String title;
    String description;
}
