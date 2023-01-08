package com.jira.AccoliteJiraBackend.Service;

import com.jira.AccoliteJiraBackend.Base.EditJira;
import com.jira.AccoliteJiraBackend.Base.Jira;
import com.jira.AccoliteJiraBackend.Repository.EmployeeRepository;
import com.jira.AccoliteJiraBackend.Repository.JiraRepository;
import com.jira.AccoliteJiraBackend.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JiraService {

    @Autowired
    private JiraRepository jiraRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;




    /**
     * Performs adding a jira to the database
     * it maps the jira to the specific project provided
     * by default all the jiras created are active and the jira points would be zero
     * every jira of type epic has a flag = 1 and type task has a flag = 0
     * every jira of type task is connected with a jira of type epic ( self join )
     * every jira has a mapping to an employee , based on that the jiraassignee would be set
     * by default when a jira is created , it will be in the To-Do state
     * @param jira - object (JSON Form)
     */
    public Jira createJira(Jira jira){

         jira.setJiraStatus("To-Do");
         jira.setJiraprojects(projectRepository.getByProjectId(jira.getJiraprojects().getProjectId()));
         jira.setIsjiraActive(true);
         if(jira.getJiraType().equals("Epic")){
             jira.setFlag(1);
             jira.setJiraepic(null);
             jira.setEmployee(null);
             jira.setJiraLinkedIssue("None");
         }else{
             jira.setFlag(0);
         }

        return jiraRepository.save(jira);

     }


    /**
     * views all the jiras
     * findAll() returns a list of jira objects
     */
     public List<Jira> viewAllJiras(){

         return jiraRepository.findAll();
     }

    /**
     * views all the jiras by ID
     * a query is run for returning the jira object by ID
     */
     public Jira viewJiraById(String jiraId){

         Optional<Jira> jiraObj = this.jiraRepository.findByJiraId(jiraId);
         return jiraObj.get();
     }

    /**
     * deletes a jira by ID
     * after a jira is done, it will be deleted/moved to a bin
     */
     public void deleteJiraById(String jiraId){

        jiraRepository.deleteByJiraId(jiraId);
     }

    /**
     * updates a specific jira
     * unless the jira is completely done the jira would be active
     */
     public void updateJira(String jiraId, EditJira editJira){

         Optional<Jira> jiraObj = jiraRepository.findByJiraId(jiraId);

          if(!(editJira.getJiraDescriptionUpdate()).equals(jiraObj.get().getJiraDescription())){
                  jiraObj.get().setJiraDescription(editJira.getJiraDescriptionUpdate());
          }
          if((editJira.getJiraPriorityUpdate())!=(jiraObj.get().getJiraPriority())){
                  jiraObj.get().setJiraPriority(editJira.getJiraPriorityUpdate());
          }
          if(!(editJira.getJiraStatusUpdate()).equals(jiraObj.get().getJiraStatus())){
                  jiraObj.get().setJiraStatus(editJira.getJiraStatusUpdate());
          }
          if(!(editJira.getJiraReporterUpdate()).equals(jiraObj.get().getJiraReporter())){
                  jiraObj.get().setJiraReporter(editJira.getJiraReporterUpdate());
          }
          if(!(editJira.getJiraLinkedIssueUpdate()).equals(jiraObj.get().getJiraLinkedIssue())){
                 jiraObj.get().setJiraLinkedIssue(editJira.getJiraLinkedIssueUpdate());
          }

          boolean isTask = (jiraObj.get().getJiraType()).equalsIgnoreCase("Task");

          if(isTask){
               jiraObj.get().setEmployee(editJira.getEmployeeUpdate());
          }else {
               jiraObj.get().setEmployee(null);
          }

     }


}
