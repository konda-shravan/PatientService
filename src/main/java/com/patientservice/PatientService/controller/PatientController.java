package com.patientservice.PatientService.controller;

import com.patientservice.PatientService.dto.PatientDTO;
import com.patientservice.PatientService.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    @PostMapping
    public PatientDTO create(@RequestBody PatientDTO patient) {
        return service.save(patient);
    }

    @GetMapping("/{id}")
    public PatientDTO get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public PatientDTO update(@PathVariable Long id, @RequestBody PatientDTO patient) {
        return service.update(id, patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public Page<PatientDTO> search(@RequestParam String lastname, Pageable pageable) {
        return service.searchByLastName(lastname, pageable);
    }
}
