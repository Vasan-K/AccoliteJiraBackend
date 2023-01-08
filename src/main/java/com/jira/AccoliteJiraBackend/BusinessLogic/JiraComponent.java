package com.jira.AccoliteJiraBackend.BusinessLogic;

import com.jira.AccoliteJiraBackend.Base.Jira;
import com.jira.AccoliteJiraBackend.Repository.JiraRepository;
import com.jira.AccoliteJiraBackend.Repository.ProjectRepository;
import com.jira.AccoliteJiraBackend.Repository.SprintRepository;
import com.jira.AccoliteJiraBackend.Service.JiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



@Service
@Transactional
public class JiraComponent {

    @Autowired
    private JiraService jiraService;
    @Autowired
    private JiraRepository jiraRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SprintComponent sprintComponent;

    @Autowired
    private SprintRepository sprintRepository;


    /**
     * It returns the projectId by passing the projectLabel
     * it runs a query that finds the projectID
     *
     * @param projectLabel - string datatype
     */
    public String returnProjectId(String projectLabel) {

        return projectRepository.findByProjectId(projectLabel);
    }


    /**
     * It returns a list of jiras
     * it returns all the epics under a project by taking projectId as a parameter
     * A Custom query is run that finds the jiras of type "EPIC" under a project
     *
     * @param projectId - string datatype
     */
    public List<Jira> viewAllEpicsOfProject(String projectId) {

        return this.jiraRepository.findByJiraprojectsProjectIdAndJiraType(projectId, "Epic");
    }


    /**
     * It returns a list of jiras
     * it returns all the EPICS
     * A Query is run that finds the jiras of type "EPIC"
     */
    public List<Jira> viewAllEpics() {

        return this.jiraRepository.findByJiraType("Epic");
    }


    /**
     * It returns a list of jiras
     * it returns all the TASKS
     * A Query is run that finds the jiras of type "TASK"
     */
    public List<Jira> viewAllTasks() {

        return this.jiraRepository.findByJiraType("Task");
    }


    /**
     * It returns a list of jiras
     * it returns all the Tasks under an EPIC
     * A Custom Query is run that finds the jiras of type "TASK" under the specific EPIC
     *
     * @param jiraId - String datatype
     */
    public List<Jira> viewTasksOfEpic(String jiraId) {

        return this.jiraRepository.findByJiraepicJiraId(jiraId);
    }


    /**
     * It returns a list of jiras
     * it returns all Jiras under an Employee
     * A Custom Query is run that finds the jiras of type "TASK" under the specific Employee
     *
     * @param alias - String datatype / the alias of the employee
     */
    public List<Jira> viewAllJirasByEmployee(String alias) {

        return this.jiraRepository.findByJiraAssigneeAndJiraType(alias, "Task");
    }


    /**
     * It returns a list of jiras
     * it returns all the EPICS under an Employee
     * A Custom Query is run that finds the jiras of type "TASK" under the specific EPIC
     * @param alias - String datatype
     */
    public List<Jira> viewAllEpicsByEmployee(String alias) {

        return this.jiraRepository.findByJiraAssigneeAndJiraType(alias, "Epic");
    }


    /**
     * It returns a list of jiras
     * it returns all the EPICS under an Employee under a particular project
     * A Custom Query is run that finds the jiras of type "EPIC" of an employee of a project
     * this is employee and project specific
     * @param employeeId - String datatype
     * @param projectId - String datatype
     */
    public List<Jira> epicOfEmployeeOfProject(String employeeId, String projectId) {

        return this.jiraRepository.findByEmployeeEmployeeIdAndJiraprojectsProjectIdAndJiraType(employeeId, projectId, "Epic");
    }


    /**
     * It returns a list of jiras
     * it returns all the TASKS under an Employee under a particular project
     * A Custom Query is run that finds the jiras of type "TASK" of an employee of a project
     * this is employee and project specific
     * @param employeeId - String datatype
     * @param projectId - String datatype
     */
    public List<Jira> tasksOfEmployeeOfProject(String employeeId, String projectId) {

        return this.jiraRepository.findByEmployeeEmployeeIdAndJiraprojectsProjectIdAndJiraType(employeeId, projectId, "Task");
    }


    /**
     * It returns the projectId
     * This takes jiraId as a parameter , returns the projectID to which the jira is mapped
     * @param jiraId- String datatype
     */
    public String projectIdByJiraId(String jiraId) {
        Optional<Jira> jiraObj = this.jiraRepository.findByJiraId(jiraId);
        Jira jira = jiraObj.get();
        return jira.getJiraprojects().getProjectId();
    }


    /**
     * It returns a list of jiras
     * it returns all the TASKS under an Employee under a particular project
     * It returns only the tasks that are not done yet in development and to-do stage
     * this is employee and project specific
     * @param employeeId - to which the tasks are mapped with
     * @param projectId - to which the employee is mapped with
     */
    public List<Jira> tasksOfEmployeeOfProjectExceptDone(String employeeId, String projectId) {

        return this.jiraRepository.getByEmployeeEmployeeIdAndJiraprojectsProjectIdAndJiraType(employeeId, projectId, "Task");
    }


    /**
     * It returns a list of jiras
     * it returns all the EPICS under an Employee under a particular project
     * A Custom Query is run that finds the jiras of type "EPIC" of an employee of an project
     * this is employee and project specific
     * @param sprintId - String datatype
     */
    public List<Jira> viewSprintTasks(String sprintId) {
        return this.jiraRepository.findBySprintOfJirasSprintIdAndJiraType(sprintId,"Task");
    }


    /**
     * Performs updating the jiraStatus of Epics
     * Every Jira of type Epic has a flag of 1 at the time of creation,and it'll be in To-Do State
     * A List of jiras of type Tasks will be mapped to this Epic and will be in To-Do State
     * if any one of the task moves to another state say "In Progress" or "In Done",
     * the jiraState of the relevant EPIC should change by itself.
     * the changing of jiraState of the EPIC is monitored by the flag which is updated based on the,
     * jiraState of the respective Jiras of type Tasks
     * this is employee and project specific
     * @param jiraId - String datatype
     */
    public void updateEpicInEpicView(String jiraId) {

        Optional<Jira> jiraEpicObj = this.jiraRepository.findByJiraId(jiraId);
        List<String> stageList = Arrays.asList("To-Do", "In Progress", "Done");
        int dev = 0, done = 0;

        if (jiraEpicObj.isPresent()) {
            int flagCheck = this.jiraRepository.getByJiraId(jiraId);
            List<Jira> jiraTasks = this.jiraRepository.findByJiraepicJiraId(jiraId);

            if (flagCheck == 1) {
                for (Jira j : jiraTasks) {
                    if ((j.getJiraStatus()).equals(stageList.get(1))) {
                        dev++;
                    }
                }
                if (dev > 0) {
                    jiraEpicObj.get().setFlag(2);
                    jiraEpicObj.get().setJiraStatus("In Progress");

                }
            }

            if (flagCheck == 2) {
                for (Jira j : jiraTasks) {
                    if ((j.getJiraStatus()).equals(stageList.get(2))) {
                        done++;
                    }
                }
                if (jiraTasks.size() == done) {
                    jiraEpicObj.get().setFlag(3);
                    jiraEpicObj.get().setJiraStatus("Done");

                }

            }


        }
    }


    /**
     * It returns a list of jiras
     * it returns all the jiras of type Tasks under an Employee which are left unmapped to any sprint
     * this is employee specific
     * @param employeeId - String datatype
     */
    public List<Jira> jirasUnAddedToSprints(String employeeId,String projectId) {

        List<String> listOfEmp = this.jiraRepository.findByEmployeeEmployeeIdAndJiraTypeAndJiraprojectsProjectId(employeeId,"Task",projectId);
        List<Jira> jirasLeft = this.jiraRepository.getUnAddedSprints();
        List<Jira> jirasUnAdded = new ArrayList<>();

        for ( int i=0;i<jirasLeft.size();i++){
            for(int j=0;j<listOfEmp.size();j++){
                if( (jirasLeft.get(i).getJiraId()).equals((listOfEmp.get(j)))){
                    jirasUnAdded.add(jirasLeft.get(i));
                }
            }
        }

        return jirasUnAdded;

    }
}





