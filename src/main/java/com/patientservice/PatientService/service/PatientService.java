package com.patientservice.PatientService.service;

import com.patientservice.PatientService.dto.PatientDTO;
import com.patientservice.PatientService.entity.Patient;
import com.patientservice.PatientService.mapper.PatientMapper;
import com.patientservice.PatientService.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;

    /**
     * SAVE / CREATE
     * Clear all caches because new data added
     */
    @CacheEvict(value = {"patients", "patient-search"}, allEntries = true)
    public PatientDTO save(PatientDTO dto){
        Patient patient = mapper.toEntity(dto);   // MapStruct handles conversion
        Patient saved = repository.save(patient);
        return mapper.toDto(saved);
    }

    /**
     * GET BY ID
     * Cached in Redis
     */
    @Cacheable(value = "patients", key = "#id")
    public PatientDTO getById(Long id){
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return mapper.toDto(patient);
    }

    /**
     * SEARCH BY LAST NAME (PAGINATED)
     * Cached in Redis
     */
    @Cacheable(
            value = "patient-search",
            key = "#lastName + '_' + #pageable.pageNumber + '_' + #pageable.pageSize + '_' + #pageable.sort"
    )
    public Page<PatientDTO> searchByLastName(String lastName, Pageable pageable) {
        Page<Patient> patients = repository.findByLastnameIgnoreCase(lastName, pageable);
        return patients.map(mapper::toDto);
    }

    /**
     * UPDATE
     * Evict cache for this patient
     */
    @CacheEvict(value = {"patients", "patient-search"}, allEntries = true)
    public PatientDTO update(Long id, PatientDTO dto){
        Patient existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Patient patient = mapper.toEntity(dto);
        patient.setId(existing.getId()); // preserve ID
        Patient updated = repository.save(patient);

        return mapper.toDto(updated);
    }

    /**
     * DELETE
     * Remove from cache
     */
    @CacheEvict(value = {"patients", "patient-search"}, allEntries = true)
    public void delete(Long id){
        repository.deleteById(id);
    }
}
