package com.esm.to_do_list.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskRecordDto(@NotBlank String task, String description, Integer status) {
}
