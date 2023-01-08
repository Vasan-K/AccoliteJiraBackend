package com.jira.AccoliteJiraBackend.Controller;

import com.jira.AccoliteJiraBackend.Base.EditJira;
import com.jira.AccoliteJiraBackend.Base.Jira;
import com.jira.AccoliteJiraBackend.BusinessLogic.JiraComponent;
import com.jira.AccoliteJiraBackend.Service.JiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
//@CrossOrigin("")
@RequestMapping(value = PathConstants.jiraPath)
public class JiraController {

    @Autowired
    public JiraService jiraService;

    @Autowired
    public JiraComponent jiraComponent;


    @GetMapping(value = PathConstants.getAllJirasPath,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Jira>> viewAllJiras(){
           return new ResponseEntity<List<Jira>>(jiraService.viewAllJiras(), HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.getProjectIdPath,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> returnProjectId(@PathVariable("projectLabel") String projectLabel){
            return new ResponseEntity<>(jiraComponent.returnProjectId(projectLabel),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.getJiraPath,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Jira> viewJiraById(@PathVariable("jiraId") String jiraId){
            return new ResponseEntity<>(jiraService.viewJiraById(jiraId),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.allEpicsOfProjectPath,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Jira>> viewAllEpicsOfProject(@PathVariable("projectId") String projectId){
            return new ResponseEntity<>(jiraComponent.viewAllEpicsOfProject(projectId),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.allTasksPath,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Jira>> viewAllTasks(){
           return new ResponseEntity<>(jiraComponent.viewAllTasks(),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.allEpicPath,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Jira>> viewAllEpics(){
           return new ResponseEntity<>(jiraComponent.viewAllEpics(),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.jirasOfEmpPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Jira>> viewAllJirasByEmployee(@PathVariable("jiraAssignee") String jiraAssignee){
          return new ResponseEntity<>(jiraComponent.viewAllJirasByEmployee(jiraAssignee),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.epicsOfEmpPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Jira>> viewAllEpicsByEmployee(@PathVariable("jiraAssignee") String jiraAssignee){
        return new ResponseEntity<>(jiraComponent.viewAllEpicsByEmployee(jiraAssignee),HttpStatus.OK);
    }

    @PostMapping(value = PathConstants.addJiraPath,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Jira> createJira(@RequestBody Jira jira){
         return new ResponseEntity<>(jiraService.createJira(jira),HttpStatus.OK);
    }

    @PostMapping(value = PathConstants.updateJiraPath,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    private void updateJira(@PathVariable("jiraId") String jiraId,@RequestBody EditJira editJira){
                jiraService.updateJira(jiraId,editJira);
    }

    @PutMapping(value = PathConstants.epicStatusPath)
    private void updateEpicInEpicView(@PathVariable("jiraId") String jiraId){
               jiraComponent.updateEpicInEpicView(jiraId);
    }

    @DeleteMapping(value = PathConstants.deleteJiraPath,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    private void deleteJiraById(@PathVariable("jiraId") String jiraId){
              jiraService.deleteJiraById(jiraId);
    }

    @GetMapping(value = PathConstants.epicEmpPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Jira>> epicOfEmployeeOfProject(@PathVariable("employeeId")String employeeId , @PathVariable("projectId") String projectId){
        return new ResponseEntity<>(jiraComponent.epicOfEmployeeOfProject(employeeId, projectId),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.taskEmpPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Jira>> tasksOfEmployeeOfProject(@PathVariable("employeeId")String employeeId , @PathVariable("projectId") String projectId){
        return new ResponseEntity<>(jiraComponent.tasksOfEmployeeOfProject(employeeId, projectId),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.taskEmpNotDonePath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Jira>> tasksOfEmployeeOfProjectExceptDone(@PathVariable("employeeId")String employeeId , @PathVariable("projectId") String projectId){
        return new ResponseEntity<>(jiraComponent.tasksOfEmployeeOfProjectExceptDone(employeeId, projectId),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.projectIdJiraIdPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> projectIdByJiraId(@PathVariable("jiraId") String jiraId){
        return new ResponseEntity<>(jiraComponent.projectIdByJiraId(jiraId),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.tasksOfEpicPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Jira>> viewTasksOfEpic(@PathVariable("jiraId") String jiraId){
        return new ResponseEntity<>(jiraComponent.viewTasksOfEpic(jiraId),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.viewSprintTaskPath,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Jira>> viewSprintTasks(@PathVariable("sprintId") String sprintId){
        return new ResponseEntity<List<Jira>>(jiraComponent.viewSprintTasks(sprintId),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.viewSprintUnAddedTasksPath,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Jira>> jirasUnAddedToSprints(@PathVariable("employeeId") String employeeId,@PathVariable("projectId") String projectId){
        return new ResponseEntity<List<Jira>>(jiraComponent.jirasUnAddedToSprints(employeeId,projectId),HttpStatus.OK);
    }


}
