package com.movie.golden.web.request;

import jakarta.validation.constraints.NotBlank;

public record MovieRequest(
        @NotBlank(message = "O campo não pode estar em branco.")
        String name,
        @NotBlank(message = "O campo não pode estar em branco.")
        String description,
        @NotBlank(message = "O campo não pode estar em branco.")
        String gender) {
}
