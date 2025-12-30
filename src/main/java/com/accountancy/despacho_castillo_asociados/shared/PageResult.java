package com.accountancy.despacho_castillo_asociados.shared;

import java.util.List;

// Dominio (framework-agnostic)
public record PageResult<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {}
