package com.patientservice.PatientService.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PatientAddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String province;
    private String cellPhone;
    private String email;
    private String type;
}

