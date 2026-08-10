package com.org.example.portfoliobackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private String techStack;
    private String githubUrl;
    private String liveUrl;
    private String imageUrl;
}
