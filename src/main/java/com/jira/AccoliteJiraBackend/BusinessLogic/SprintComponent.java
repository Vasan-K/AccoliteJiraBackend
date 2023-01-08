package com.jira.AccoliteJiraBackend.BusinessLogic;

import com.jira.AccoliteJiraBackend.Base.Jira;
import com.jira.AccoliteJiraBackend.Base.Project;
import com.jira.AccoliteJiraBackend.Base.Sprint;
import com.jira.AccoliteJiraBackend.Repository.JiraRepository;
import com.jira.AccoliteJiraBackend.Repository.ProjectRepository;
import com.jira.AccoliteJiraBackend.Repository.SprintRepository;
import com.jira.AccoliteJiraBackend.Service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class SprintComponent {

    @Autowired
    private SprintService sprintService;
    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private JiraRepository jiraRepository;




    /**
     * Performs adding a jira to a sprint
     * @param sprintId - to which the jira must be mapped
     * @param jiraId - the id of jira which must be added
     */
    public ResponseEntity<Sprint> mapJiraSprints(String sprintId, String jiraId) {

        Optional<Sprint> sprintObj = this.sprintRepository.findBySprintId(sprintId);
        Optional<Jira> jiraObj = this.jiraRepository.findByJiraId(jiraId);

        if (sprintObj.isPresent() && jiraObj.isPresent()) {

            Sprint sprint = sprintObj.get();
            Jira jiras = jiraObj.get();

            List<Jira> j = sprint.getJirasOfSprint();
            sprint.getJirasOfSprint().add(jiras);
            sprintRepository.save(sprint);
            return new ResponseEntity<>(sprint, HttpStatus.CREATED);

        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }


    /**
     * Performs adding a sprint to a project
     * @param sprintId - sprint to be added
     * @param projectId - the project to which the sprint must be added
     */
    public ResponseEntity<Sprint> addSprintToProject(String sprintId, String projectId) {
        Optional<Sprint> sprintObj = this.sprintRepository.findBySprintId(sprintId);
        Optional<Project> projectObj = this.projectRepository.queryByProjectId(projectId);
        if (projectObj.isPresent() && sprintObj.isPresent()) {
            Sprint sprints = sprintObj.get();
            Project project = projectObj.get();
            sprints.setSprintOfProject(project);
            sprintRepository.save(sprints);
            return new ResponseEntity<>(sprints, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    /**
     * It returns a list of jiras
     * it returns all the jiras of type Task under the Current Running Sprint
     * A Custom Query is run that finds the jiras of type "TASK" of the current sprint
     * current active sprint will have a boolean, that acts as an indicator
     * @param sprintId - String datatype
     */
    public List<Jira> viewCurrentSprintTasks(String projectId){

        String currentSprintId = this.sprintRepository.findBySprintOfProjectProjectId(projectId);
        return this.jiraRepository.findBySprintOfJirasSprintIdAndJiraType(currentSprintId,"Task");

    }









}

