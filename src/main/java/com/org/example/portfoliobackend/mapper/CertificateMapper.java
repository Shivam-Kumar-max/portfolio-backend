package com.org.example.portfoliobackend.mapper;

import com.org.example.portfoliobackend.dto.CertificateDto;
import com.org.example.portfoliobackend.entity.Certificate;

public class CertificateMapper {
    public static CertificateDto mapToCertificateDto(Certificate certificate){
        return new CertificateDto(
                certificate.getId(),
                certificate.getImage(),
                certificate.getDescription()
        );
    }

    public static Certificate mapToCertificate(CertificateDto certificateDto){
        return new Certificate(
                certificateDto.getId(),
                certificateDto.getImage(),
                certificateDto.getDescription()
        );
    }
}
