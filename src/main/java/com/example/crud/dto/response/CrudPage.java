package com.example.crud.dto.response;

import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class CrudPage<T> {
    private List<T> contents;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalCount;

    public static <T> CrudPage<T> of(Page<T> pagedContents) {
        CrudPage<T> converted = new CrudPage<>();
        converted.contents = pagedContents.getContent();
        converted.pageNumber = pagedContents.getNumber();
        converted.pageSize = pagedContents.getSize();
        converted.totalPages = pagedContents.getTotalPages();
        converted.totalCount = pagedContents.getTotalElements();
        return converted;
    }
}
