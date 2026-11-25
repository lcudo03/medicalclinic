package com.lcudo03.medicalclinic;

import com.lcudo03.medicalclinic.dto.CreatePatientDTO;
import com.lcudo03.medicalclinic.dto.PatientDTO;
import com.lcudo03.medicalclinic.dto.UpdatePatientDTO;
import com.lcudo03.medicalclinic.mapper.PatientMapper;
import com.lcudo03.medicalclinic.model.Patient;
import com.lcudo03.medicalclinic.repository.PatientRepository;
import com.lcudo03.medicalclinic.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PatientServiceTest {

    PatientService patientService;
    PatientRepository patientRepository;
    PatientMapper patientMapper;

    @BeforeEach
    void setup() {
        this.patientRepository = Mockito.mock(PatientRepository.class);
        this.patientMapper = Mappers.getMapper(PatientMapper.class);
        this.patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    void getPatients_DataCorrect_PatientsReturned() {
        //given
        Patient patient1 = new Patient(
                "jan@gmail.com",
                "pass",
                "123",
                "Jan",
                "Nowacki",
                "123456789",
                LocalDate.of(2000, 1, 1)
        );

        Patient patient2 = new Patient(
                "anna@gmail.com",
                "password",
                "321",
                "Anna",
                "Nowak",
                "987654321",
                LocalDate.of(2003, 4, 12)
        );

        List<Patient> patientList = List.of(patient1, patient2);


        //when
        Mockito.when(patientRepository.findAll()).thenReturn(patientList);

        List<PatientDTO> result = patientService.getPatients();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, result.size()),
                () -> Assertions.assertEquals("jan@gmail.com", result.get(0).email()),
                () -> Assertions.assertEquals("Jan", result.get(0).firstName()),
                () -> Assertions.assertEquals("Nowacki", result.get(0).lastName()),
                () -> Assertions.assertEquals("anna@gmail.com", result.get(1).email()),
                () -> Assertions.assertEquals("Anna", result.get(1).firstName()),
                () -> Assertions.assertEquals("Nowak", result.get(1).lastName())
        );


    }


    @Test
    void getPatientDTO_PatientExists_PatientReturned() {
        // given
        String email = "jan@gmail.com";

        Patient patient = new Patient(
                email,
                "password123",
                "ABC123",
                "Jan",
                "Kowalski",
                "123456789",
                LocalDate.of(2000, 1, 1)
        );

        Mockito.when(patientRepository.findByEmail(email))
                .thenReturn(Optional.of(patient));

        // when
        PatientDTO result = patientService.getPatientDTO(email);

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(email, result.email()),
                () -> Assertions.assertEquals("ABC123", result.idCardNo()),
                () -> Assertions.assertEquals("Jan", result.firstName()),
                () -> Assertions.assertEquals("Kowalski", result.lastName()),
                () -> Assertions.assertEquals("123456789", result.phoneNumber()),
                () -> Assertions.assertEquals(LocalDate.of(2000, 1, 1), result.birthday())
        );
    }


    @Test
    void addPatient_ValidData_PatientAdded() {
        // given
        CreatePatientDTO dto = new CreatePatientDTO(
                "jan@gmail.com",
                "password123",
                "ABC123",
                "Jan",
                "Kowalski",
                "123456789",
                LocalDate.of(2000, 1, 1)
        );

        Patient patient = new Patient(
                "jan@gmail.com",
                "password123",
                "ABC123",
                "Jan",
                "Kowalski",
                "123456789",
                LocalDate.of(2000, 1, 1)
        );

        Mockito.when(patientRepository.findByEmail(dto.email()))
                .thenReturn(Optional.empty());

        Mockito.when(patientRepository.addPatient(Mockito.any(Patient.class)))
                .thenReturn(patient);

        // when
        PatientDTO result = patientService.addPatient(dto);

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals("jan@gmail.com", result.email()),
                () -> Assertions.assertEquals("ABC123", result.idCardNo()),
                () -> Assertions.assertEquals("Jan", result.firstName()),
                () -> Assertions.assertEquals("Kowalski", result.lastName()),
                () -> Assertions.assertEquals("123456789", result.phoneNumber()),
                () -> Assertions.assertEquals(LocalDate.of(2000, 1, 1), result.birthday())
        );

        Mockito.verify(patientRepository).addPatient(Mockito.any(Patient.class));
    }

    @Test
    void modifyPatient_ValidData_PatientUpdated() {
        // given
        String email = "jan@gmail.com";

        Patient patient = new Patient(
                email,
                "password123",
                "ABC123",
                "Jan",
                "Kowalski",
                "123456789",
                LocalDate.of(2000, 1, 1)
        );

        UpdatePatientDTO dto = new UpdatePatientDTO(
                "XYZ789",
                "Janusz",
                "Nowak",
                "987654321",
                LocalDate.of(1999, 5, 15)
        );

        Mockito.when(patientRepository.findByEmail(email))
                .thenReturn(Optional.of(patient));

        // when
        PatientDTO result = patientService.modifyPatient(email, dto);

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(email, result.email()),
                () -> Assertions.assertEquals("XYZ789", result.idCardNo()),
                () -> Assertions.assertEquals("Janusz", result.firstName()),
                () -> Assertions.assertEquals("Nowak", result.lastName()),
                () -> Assertions.assertEquals("987654321", result.phoneNumber()),
                () -> Assertions.assertEquals(LocalDate.of(1999, 5, 15), result.birthday())
        );

        Mockito.verify(patientRepository).findByEmail(email);
    }
}
