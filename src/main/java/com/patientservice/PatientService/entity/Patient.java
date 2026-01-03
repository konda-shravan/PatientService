package com.patientservice.PatientService.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient")
@Data
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Long referredByProviderId;
    private Long referredByProviderClinicId;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PatientInsurance> patientInsurance_Patient;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PatientAddress> patientAddresses_Patient;
}
