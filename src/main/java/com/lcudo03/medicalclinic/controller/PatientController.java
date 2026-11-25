package com.lcudo03.medicalclinic.controller;

import com.lcudo03.medicalclinic.dto.ChangePassword;
import com.lcudo03.medicalclinic.dto.CreatePatientDTO;
import com.lcudo03.medicalclinic.dto.PatientDTO;
import com.lcudo03.medicalclinic.dto.UpdatePatientDTO;
import com.lcudo03.medicalclinic.model.Patient;
import com.lcudo03.medicalclinic.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;


    @GetMapping
    public List<PatientDTO> getPatients() {
        return service.getPatients();
    }

    @GetMapping("/{email}")
    public PatientDTO getPatient(@PathVariable String email) {
        return service.getPatientDTO(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO addPatient(
            @Valid @RequestBody CreatePatientDTO dto) {
        return service.addPatient(dto);
    }

    @PutMapping("/{email}")
    public PatientDTO modifyPatient(
            @PathVariable String email,
            @Valid @RequestBody UpdatePatientDTO dto) {
        return service.modifyPatient(email, dto);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePatient(@PathVariable String email) {
        service.removePatient(email);
    }

    @PatchMapping("/{email}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(
            @PathVariable String email,
            @Valid @RequestBody ChangePassword request) {
        service.changePassword(email, request);
    }

}
