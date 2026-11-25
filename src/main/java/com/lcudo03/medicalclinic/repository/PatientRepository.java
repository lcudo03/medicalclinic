package com.lcudo03.medicalclinic.repository;

import com.lcudo03.medicalclinic.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PatientRepository {
    private final List<Patient> patientsList = new ArrayList<>();

    public List<Patient> findAll() {
        return new ArrayList<>(patientsList);
    }

    public Optional<Patient> findByEmail(String email) {
        if (email == null || email.isBlank()) {
            return Optional.empty();
        }

        return patientsList.stream()
                .filter(patient -> email.equals(patient.getEmail()))
                .findFirst();
    }

    public Patient addPatient(Patient patient) {
        patientsList.add(patient);
        return patient;
    }

    public boolean deleteByEmail(String email) {
        return findByEmail(email)
                .map(patientsList::remove)
                .orElse(false);
    }

    public Patient modifyByEmail(Patient patient, Patient updatedPatient) {
        patient.setFirstName(updatedPatient.getFirstName());
        patient.setLastName(updatedPatient.getLastName());
        patient.setPhoneNumber(updatedPatient.getPhoneNumber());
        patient.setBirthday(updatedPatient.getBirthday());
        patient.setIdCardNo(updatedPatient.getIdCardNo());
        patient.setPassword(updatedPatient.getPassword());

        return patient;
    }

    public void changePassword(Patient patient, String newPassword) {
        patient.setPassword(newPassword);
    }
}

