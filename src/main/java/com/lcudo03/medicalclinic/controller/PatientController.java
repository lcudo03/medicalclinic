package com.lcudo03.medicalclinic.controller;

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
    public List<Patient> getPatients() {
        return service.getPatients();
    }

    @GetMapping("/{email}")
    public Patient getPatient(@PathVariable String email) {
        return service.getPatient(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient addPatient(@Valid @RequestBody Patient patient) {
        return service.addPatient(patient);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePatient(@PathVariable String email) {
        service.removePatient(email);
    }

    @PutMapping("/{email}")
    public Patient modifyPatient(@PathVariable String email, @Valid @RequestBody Patient updatedPatient) {
        return service.modifyPatient(email, updatedPatient);
    }
}
