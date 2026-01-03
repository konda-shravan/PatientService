package com.patientservice.PatientService.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PatientAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String province;
    private Boolean isCellPhoneValid;
    private Boolean isHomePhoneValid;
    private Boolean isWorkPhoneValid;
    private String cellPhone;
    private String homePhone;
    private String workPhone;
    private String email;
    private String type;
    private LocalDateTime auditCreatedOn;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
