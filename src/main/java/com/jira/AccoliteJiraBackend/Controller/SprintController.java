package com.jira.AccoliteJiraBackend.Controller;

import com.jira.AccoliteJiraBackend.Base.Jira;
import com.jira.AccoliteJiraBackend.Base.Sprint;
import com.jira.AccoliteJiraBackend.BusinessLogic.SprintComponent;
import com.jira.AccoliteJiraBackend.Exceptions.NoObjectException;
import com.jira.AccoliteJiraBackend.Service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
//@CrossOrigin("")
@RequestMapping(value = PathConstants.sprintPath)
public class SprintController {

    @Autowired
    public SprintService sprintService;

    @Autowired
    public SprintComponent sprintComponent;

    @PostMapping(value = PathConstants.addSprintPath,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Sprint> addSprints(@RequestBody Sprint s){
           return new ResponseEntity<>(sprintService.addSprints(s),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.viewAllSprintPath,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Sprint>> viewSprints(){
        return new ResponseEntity<>(sprintService.viewSprints(),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.viewSprintPath,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Sprint> viewSprint(@PathVariable String sprintId)  {
        try {
            return new ResponseEntity<>(sprintService.viewSprint(sprintId),HttpStatus.OK);
        } catch (NoObjectException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = PathConstants.viewCurrentSprintPath,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Jira>> viewCurrentSprintTasks(@PathVariable("projectId") String projectId){
          return new ResponseEntity<List<Jira>>(sprintComponent.viewCurrentSprintTasks(projectId),HttpStatus.OK);
    }



    @PutMapping(value = PathConstants.addJiraSprintPath,produces = MediaType.TEXT_PLAIN_VALUE)
    private ResponseEntity<Sprint> mapJiraSprints(@PathVariable("sprintId") String sprintId , @PathVariable("jiraId") String jiraId ){
        return sprintComponent.mapJiraSprints(sprintId,jiraId);
    }

    @PutMapping(value = PathConstants.addSprintProjectPath,produces = MediaType.TEXT_PLAIN_VALUE)
    private ResponseEntity<Sprint> addSprintToProject(@PathVariable("sprintId") String sprintId, @PathVariable("projectId") String projectId ){

        try {
            return sprintComponent.addSprintToProject(sprintId,projectId);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
