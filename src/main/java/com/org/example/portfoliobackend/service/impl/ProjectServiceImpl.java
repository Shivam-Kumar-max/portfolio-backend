package com.org.example.portfoliobackend.service.impl;

import com.org.example.portfoliobackend.dto.ProjectDto;
import com.org.example.portfoliobackend.entity.Project;
import com.org.example.portfoliobackend.exception.ResourceNotFoundException;
import com.org.example.portfoliobackend.mapper.ProjectMapper;
import com.org.example.portfoliobackend.repository.ProjectRepository;
import com.org.example.portfoliobackend.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = ProjectMapper.mapToProject(projectDto);
        Project saved = projectRepository.save(project);
        return ProjectMapper.mapToProjectDto(saved);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectMapper::mapToProjectDto)
                .toList();
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return ProjectMapper.mapToProjectDto(project);
    }

    @Override
    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setTechStack(projectDto.getTechStack());
        project.setGithubUrl(projectDto.getGithubUrl());
        project.setLiveUrl(projectDto.getLiveUrl());
        project.setImageUrl(projectDto.getImageUrl());

        Project updated = projectRepository.save(project);
        return ProjectMapper.mapToProjectDto(updated);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        projectRepository.delete(project);
    }
}
