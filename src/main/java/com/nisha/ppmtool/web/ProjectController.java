package com.nisha.ppmtool.web;

import com.nisha.ppmtool.domain.Project;
import com.nisha.ppmtool.services.MapValidationErrorService;
import com.nisha.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping
    public ResponseEntity<?> createNewProject(
            @Valid @RequestBody Project project,
            BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }

        Project savedProject = projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
    }
}
