package com.patientservice.PatientService.mapper;

import com.patientservice.PatientService.dto.PatientAddressDTO;
import com.patientservice.PatientService.dto.PatientDTO;
import com.patientservice.PatientService.dto.PatientInsuranceDTO;
import com.patientservice.PatientService.entity.Patient;
import com.patientservice.PatientService.entity.PatientAddress;
import com.patientservice.PatientService.entity.PatientInsurance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(source = "patientInsurance_Patient",target = "insurances")
    @Mapping(source = "patientAddresses_Patient",target = "addresses")
    PatientDTO toDto(Patient patient);

    Patient toEntity(PatientDTO patientDto);

    List<PatientDTO> toDtoList(List<Patient> patients);

    @Mapping(source = "payer_Payers.name", target = "payerName")
    PatientInsuranceDTO toInsuranceDto(PatientInsurance insurance);

    PatientAddressDTO toAddressDto(PatientAddress address);
}
