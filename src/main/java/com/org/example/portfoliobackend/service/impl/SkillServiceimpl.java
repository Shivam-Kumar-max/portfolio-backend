package com.org.example.portfoliobackend.service.impl;

import com.org.example.portfoliobackend.dto.SkillDto;
import com.org.example.portfoliobackend.entity.Skill;
import com.org.example.portfoliobackend.exception.ResourceNotFoundException;
import com.org.example.portfoliobackend.mapper.SkillMapper;
import com.org.example.portfoliobackend.repository.SkillRepository;
import com.org.example.portfoliobackend.service.SkillService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillServiceimpl implements SkillService {
    private final SkillRepository skillRepository;

    @Override
    public SkillDto createSkill(SkillDto skillDto) {
        Skill skill = SkillMapper.mapToSkill(skillDto);
        Skill savedSkill = skillRepository.save(skill);
        return SkillMapper.mapToSkillDto(savedSkill);
    }

    @Override
    public List<SkillDto> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(SkillMapper::mapToSkillDto)
                .toList();
    }

    @Override
    public SkillDto getSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        return SkillMapper.mapToSkillDto(skill);
    }

    @Override
    public SkillDto updateSkill(Long id, SkillDto skillDto) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));

        skill.setSkillName(skillDto.getSkillName());
        skill.setPercentage(skillDto.getPercentage());
        skill.setCategory(skillDto.getCategory());

        Skill updated = skillRepository.save(skill);
        return SkillMapper.mapToSkillDto(updated);
    }

    @Override
    public void deleteSkill(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        skillRepository.delete(skill);
    }
}
