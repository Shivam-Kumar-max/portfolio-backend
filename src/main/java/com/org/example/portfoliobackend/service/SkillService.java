package com.org.example.portfoliobackend.service;

import com.org.example.portfoliobackend.dto.SkillDto;

import java.util.List;

public interface SkillService {
    SkillDto createSkill(SkillDto skillDto);
    List<SkillDto> getAllSkills();
    SkillDto getSkillById(Long id);
    SkillDto updateSkill(Long id, SkillDto skillDto);
    void deleteSkill(Long id);
}
