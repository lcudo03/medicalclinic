package com.lcudo03.medicalclinic.dto;

import java.time.LocalDate;

public record PatientDTO(
        String email,
        String idCardNo,
        String firstName,
        String lastName,
        String phoneNumber,
        LocalDate birthday
) {

}
