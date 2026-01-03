package com.patientservice.PatientService.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PatientInsuranceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accountNumber;
    private String extraInfo1;
    private String payerName;
}
