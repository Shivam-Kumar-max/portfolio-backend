package com.org.example.portfoliobackend.service;

import com.org.example.portfoliobackend.dto.CertificateDto;

import java.util.List;

public interface CertificateService {

    CertificateDto createCertificate(CertificateDto certificateDto);

    List<CertificateDto> getAllCertificates();

    CertificateDto getCertificateById(Long id);

    CertificateDto updateCertificate(Long id,CertificateDto certificateDto);

    void deleteCertificate(Long id);
 }
