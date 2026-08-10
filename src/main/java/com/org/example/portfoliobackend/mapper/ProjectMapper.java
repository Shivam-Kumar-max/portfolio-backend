package com.org.example.portfoliobackend.mapper;

import com.org.example.portfoliobackend.dto.ProjectDto;
import com.org.example.portfoliobackend.entity.Project;

public class ProjectMapper {

    public static ProjectDto mapToProjectDto(Project project) {
        return new ProjectDto(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getTechStack(),
                project.getGithubUrl(),
                project.getLiveUrl(),
                project.getImageUrl()
        );
    }

    public static Project mapToProject(ProjectDto projectDto) {
        return new Project(
                projectDto.getId(),
                projectDto.getTitle(),
                projectDto.getDescription(),
                projectDto.getTechStack(),
                projectDto.getGithubUrl(),
                projectDto.getLiveUrl(),
                projectDto.getImageUrl()
        );
    }
}
