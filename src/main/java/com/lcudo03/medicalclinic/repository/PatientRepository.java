package com.lcudo03.medicalclinic.repository;

import com.lcudo03.medicalclinic.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

@Repository
public class PatientRepository {
    private final List<Patient> patientsList = new ArrayList<>();

    public List<Patient> findAll() {
        return new ArrayList<>(patientsList);
    }

    public Patient findByEmail(String email){
        return patientsList.stream()
                .filter(patient -> patient.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public void createNewPatient(Patient newPatient){
        patientsList.add(newPatient);
    }

    public void deleteByEmail(String email){
        patientsList.removeIf(patient -> patient.getEmail().equals(email));
    }

    public void modifyByEmail(String email, Patient updatedPatient){
        Patient patient = findByEmail(email);
        if (patient != null){
            BeanUtils.copyProperties(updatedPatient, patient);
        }
    }
}
