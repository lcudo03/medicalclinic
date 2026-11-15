package com.lcudo03.medicalclinic.service;

import com.lcudo03.medicalclinic.model.Patient;
import com.lcudo03.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }
    public Patient getPatient(String email){
        return patientRepository.findByEmail(email);
    }

    public void addPatient(Patient newPatient){
        patientRepository.createNewPatient(newPatient);
    }

    public void removePatient(String email){
        patientRepository.deleteByEmail(email);
    }

    public void modifyPatient(String email, Patient updatedPatient){
        patientRepository.modifyByEmail(email, updatedPatient);
    }

}
