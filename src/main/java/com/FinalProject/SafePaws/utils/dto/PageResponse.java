package com.FinalProject.SafePaws.utils.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageResponse<T> {

    private List<T> content;
    private Long totalElements;
    private Integer totalPages;
    private Integer size;
    private Integer page;

    public PageResponse(Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.page = page.getNumber();
        this.size = page.getSize();
    }

}
