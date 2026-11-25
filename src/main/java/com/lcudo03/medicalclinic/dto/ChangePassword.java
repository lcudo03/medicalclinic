package com.lcudo03.medicalclinic.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePassword(@NotBlank String oldPassword, @NotBlank String newPassword) {
}

