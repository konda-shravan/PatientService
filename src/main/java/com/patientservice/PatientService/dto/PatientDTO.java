package com.patientservice.PatientService.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.io.Serializable;

@Data
public class PatientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstname;
    private String lastname;
    private String middlename;
    private String gender;
    private LocalDate dateOfBirth;
    private String chartNumber;
    private String title;
    private Boolean portalLogin;
    private String primaryEmail;
    private List<PatientInsuranceDTO> insurances;
    private List<PatientAddressDTO> addresses;
}
