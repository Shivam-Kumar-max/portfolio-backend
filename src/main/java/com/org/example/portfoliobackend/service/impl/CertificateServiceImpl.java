package com.org.example.portfoliobackend.service.impl;

import com.org.example.portfoliobackend.dto.CertificateDto;
import com.org.example.portfoliobackend.entity.Certificate;
import com.org.example.portfoliobackend.exception.ResourceNotFoundException;
import com.org.example.portfoliobackend.mapper.CertificateMapper;
import com.org.example.portfoliobackend.repository.CertificateRepository;
import com.org.example.portfoliobackend.service.CertificateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private  CertificateRepository certificateRepository;

    @Override
    public CertificateDto createCertificate(CertificateDto certificateDto) {
        Certificate certificate = CertificateMapper.mapToCertificate(certificateDto);
        Certificate saved = certificateRepository.save(certificate);
        return CertificateMapper.mapToCertificateDto(saved);
    }

    @Override
    public List<CertificateDto> getAllCertificates() {
        return certificateRepository.findAll().stream().map(CertificateMapper::mapToCertificateDto).toList();
    }

    @Override
    public CertificateDto getCertificateById(Long id) {
        Certificate certificate=certificateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Certificate not found with id:" +id));
        return CertificateMapper.mapToCertificateDto(certificate);
    }

    @Override
    public CertificateDto updateCertificate(Long id, CertificateDto certificateDto) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Certificate not found with id:" +id));

        certificate.setImage(certificateDto.getImage());
        certificate.setDescription(certificateDto.getDescription());
        Certificate updated = certificateRepository.save(certificate);
        return CertificateMapper.mapToCertificateDto(updated);
    }

    @Override
    public void deleteCertificate(Long id) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Certificate not found with id:" +id));
        certificateRepository.delete(certificate);
    }

}
