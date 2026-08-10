package com.org.example.portfoliobackend.mapper;


import com.org.example.portfoliobackend.dto.SkillDto;
import com.org.example.portfoliobackend.entity.Skill;

public class SkillMapper {

    public static SkillDto mapToSkillDto(Skill skill){
        return new SkillDto(skill.getId(),
                skill.getSkillName(),
                skill.getPercentage(),
                skill.getCategory());
    }

    public static Skill mapToSkill(SkillDto skillDto){
        return new Skill(
                skillDto.getId(),
                skillDto.getSkillName(),
                skillDto.getPercentage(),
                skillDto.getCategory()
        );
    }
}
