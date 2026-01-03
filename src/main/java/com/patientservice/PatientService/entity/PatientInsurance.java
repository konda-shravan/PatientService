package com.patientservice.PatientService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PatientInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String extraInfo1;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private Payer payer_Payers;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
