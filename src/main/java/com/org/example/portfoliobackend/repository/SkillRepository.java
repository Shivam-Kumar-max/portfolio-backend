package com.org.example.portfoliobackend.repository;

import com.org.example.portfoliobackend.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
