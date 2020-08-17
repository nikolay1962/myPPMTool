package com.nisha.ppmtool.services;

import com.nisha.ppmtool.domain.Project;
import com.nisha.ppmtool.exceptions.ProjectIdException;
import com.nisha.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception ex) {
            throw new ProjectIdException("Project Id '" + project.getProjectIdentifier() + "' already exists.");
        }
    }
}
