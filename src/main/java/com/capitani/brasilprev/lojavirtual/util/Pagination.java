package com.capitani.brasilprev.lojavirtual.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

public class Pagination {
    public static Pageable get(int page, int size, String order, String sort) {
        return PageRequest.of(page, size, order.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sort);
    }
}
