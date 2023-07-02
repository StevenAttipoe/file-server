package com.amalitech.fileserver.Utils;

import lombok.Data;

@Data
public class PaginationResult<T> {
    private T data;
    private int totalPages;

}
