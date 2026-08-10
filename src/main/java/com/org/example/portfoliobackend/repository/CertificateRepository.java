package com.org.example.portfoliobackend.repository;

import com.org.example.portfoliobackend.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate,Long> {
}
