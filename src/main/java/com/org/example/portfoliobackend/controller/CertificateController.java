package com.org.example.portfoliobackend.controller;

import com.org.example.portfoliobackend.dto.CertificateDto;
import com.org.example.portfoliobackend.repository.CertificateRepository;
import com.org.example.portfoliobackend.service.CertificateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/certificates")
public class CertificateController {
    private final CertificateService certificateService;

    //Admin only - add a certificate
    @PostMapping
    public ResponseEntity<CertificateDto> createCertificate(@Valid @RequestBody CertificateDto certificateDto){
        return  new ResponseEntity<>(certificateService.createCertificate(certificateDto), HttpStatus.CREATED);
    }

    //public - list all certificate
    @GetMapping
    public ResponseEntity<List<CertificateDto>> getAllCertificates(){
        return ResponseEntity.ok(certificateService.getAllCertificates());
    }

    //public -get a single certificate
    @GetMapping("/{id}")
    public ResponseEntity<CertificateDto> getCertificateById(@PathVariable Long id){
        return ResponseEntity.ok(certificateService.getCertificateById(id));
    }

    //Admin only - update a certificate
    @PutMapping("/{id}")
    public ResponseEntity<CertificateDto> updateCertificate(@PathVariable Long id,@Valid @RequestBody CertificateDto certificateDto){
        return ResponseEntity.ok(certificateService.updateCertificate(id, certificateDto));
    }

    //Admin only - delete a certificate
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCertificate(@PathVariable Long id){
        certificateService.deleteCertificate(id);
        return ResponseEntity.ok("Certificate deleted Successfully");
    }
}
