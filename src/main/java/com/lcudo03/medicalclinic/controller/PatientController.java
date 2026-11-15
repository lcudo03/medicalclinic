package com.lcudo03.medicalclinic.controller;

import com.lcudo03.medicalclinic.model.Patient;
import com.lcudo03.medicalclinic.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    @GetMapping
    public List<Patient> getListOfPatients(){
        return service.getPatients();
    }

    @GetMapping("/{email}")
    public Patient getPatient(@PathVariable String email){
        return service.getPatient(email);
    }
    @PostMapping
    public void addPatient(@RequestBody Patient newPatient){
        service.addPatient(newPatient);
    }

    @DeleteMapping("/{email}")
    public void removePatient(@PathVariable String email){
        service.removePatient(email);
    }

    @PutMapping("/{email}")
    public void modifyPatient(@PathVariable String email, @RequestBody Patient updatedPatient){
        service.modifyPatient(email, updatedPatient);
    }
}
