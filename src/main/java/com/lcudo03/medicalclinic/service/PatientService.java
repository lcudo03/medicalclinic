package com.lcudo03.medicalclinic.service;

import com.lcudo03.medicalclinic.exception.PatientAlreadyExistsException;
import com.lcudo03.medicalclinic.exception.PatientNotFoundException;
import com.lcudo03.medicalclinic.model.Patient;
import com.lcudo03.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatient(String email) {
        return patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException(email));
    }

    public Patient addPatient(Patient patient) {
        if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new PatientAlreadyExistsException(patient.getEmail());
        }

        return patientRepository.addPatient(patient);
    }

    public void removePatient(String email) {
        if (!patientRepository.deleteByEmail(email)) {
            throw new PatientNotFoundException(email);
        }
    }

    public Patient modifyPatient(String email, Patient patient) {
        return patientRepository.modifyByEmail(email, patient);
    }

}
