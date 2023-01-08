package com.jira.AccoliteJiraBackend.Controller;

import com.jira.AccoliteJiraBackend.Base.Project;
import com.jira.AccoliteJiraBackend.BusinessLogic.ProjectComponent;
import com.jira.AccoliteJiraBackend.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
//@CrossOrigin("")
@RequestMapping(value = PathConstants.projectPath)
public class ProjectController {

    @Autowired
    public ProjectService projectService;

    @Autowired
    public ProjectComponent projectComponent;

    @GetMapping(value = PathConstants.viewAllProjectPath, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> viewAllProjects(){
          return ResponseEntity.ok().body(this.projectService.viewAllProjects());
    }

    @GetMapping(value = PathConstants.viewProjectByIdPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> viewProjectById(@PathVariable("projectId") String projectId){
         return ResponseEntity.ok().body(this.projectService.viewProjectById(projectId));
    }

    @GetMapping(value = PathConstants.getProjectDropDownPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getProjectDropdown(){
         return ResponseEntity.ok().body(this.projectService.getProjectDropdown());
    }

    @PostMapping(value = PathConstants.addProjectPath,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> createProject(@RequestBody Project project)
    {
        try {
            projectService.createProject(project);
            return new ResponseEntity<>(project, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = PathConstants.addEmpProjectPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> addEmployeeToProject(@PathVariable("projectId") String projectId, @PathVariable("employeeId") String employeeId)
    {
        try {
            return projectComponent.addEmployeeToProject(projectId,employeeId);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = PathConstants.deleteProjectPath,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public void removeEmployeeFromProject(@PathVariable("projectId") String projectId, @PathVariable("employeeId") String employeeId){
            projectComponent.removeEmployeeFromProject(projectId,employeeId);

    }

    @GetMapping(value = PathConstants.projectbyAliasPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Project> viewAllProjectsByEmployeeId(@PathVariable("alias") String alias){
        return projectComponent.viewAllProjectsByAlias(alias);
    }
}
