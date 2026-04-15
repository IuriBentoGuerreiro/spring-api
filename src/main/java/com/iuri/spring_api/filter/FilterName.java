package com.iuri.spring_api.filter;

import jakarta.validation.constraints.NotBlank;

public record FilterName(
        @NotBlank
        String name) {}
