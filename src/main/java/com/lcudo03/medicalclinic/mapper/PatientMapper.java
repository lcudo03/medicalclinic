
package com.lcudo03.medicalclinic.mapper;

import com.lcudo03.medicalclinic.dto.*;
import com.lcudo03.medicalclinic.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toDTO(Patient patient);

    List<PatientDTO> toDTOList(List<Patient> patients);

    Patient toPatient(CreatePatientDTO dto);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updatePatient(UpdatePatientDTO dto, @MappingTarget Patient patient);
}
