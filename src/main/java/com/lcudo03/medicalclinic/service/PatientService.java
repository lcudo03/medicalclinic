package com.lcudo03.medicalclinic.service;

import com.lcudo03.medicalclinic.dto.ChangePassword;
import com.lcudo03.medicalclinic.dto.CreatePatientDTO;
import com.lcudo03.medicalclinic.dto.PatientDTO;
import com.lcudo03.medicalclinic.dto.UpdatePatientDTO;
import com.lcudo03.medicalclinic.exception.PatientAlreadyExistsException;
import com.lcudo03.medicalclinic.exception.PatientNotFoundException;
import com.lcudo03.medicalclinic.mapper.PatientMapper;
import com.lcudo03.medicalclinic.model.Patient;
import com.lcudo03.medicalclinic.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientDTO> getPatients() {
        return patientMapper.toDTOList(patientRepository.findAll());
    }

    public PatientDTO getPatientDTO(String email) {
        return patientMapper.toDTO(getPatient(email));
    }


    public PatientDTO addPatient(CreatePatientDTO dto) {
        Patient patient = patientMapper.toPatient(dto);

        if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new PatientAlreadyExistsException(patient.getEmail());
        }

        Patient savedPatient = patientRepository.addPatient(patient);

        return patientMapper.toDTO(savedPatient);
    }


    public void removePatient(String email) {
        if (!patientRepository.deleteByEmail(email)) {
            throw new PatientNotFoundException(email);
        }
    }


    public PatientDTO modifyPatient(String email, UpdatePatientDTO dto) {
        Patient patient = getPatient(email);

        patientMapper.updatePatient(dto, patient);

        return patientMapper.toDTO(patient);
    }


    public void changePassword(String email, ChangePassword request) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException(email));

        patientRepository.changePassword(patient, request.newPassword());

    }

    private Patient getPatient(String email) {
        return patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException(email));
    }

}
