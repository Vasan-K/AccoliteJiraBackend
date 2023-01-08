package com.jira.AccoliteJiraBackend.Repository;

import com.jira.AccoliteJiraBackend.Base.Jira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;



@Repository
public interface JiraRepository extends JpaRepository<Jira,String> {


        /**
         * This Query returns a list of Jiras mapped to a Sprint with a jiraType
         * @param sprintId - sprint to which the jiras are mapped
         * @param jiraType - the type of jira
         */
        List<Jira> findBySprintOfJirasSprintIdAndJiraType(String sprintId,String jiraType);


        /**
         * This Query returns a list of Jiras mapped to a Project with a jiraType
         * @param projectId - project to which the jiras are mapped
         * @param jiraType - the type of jira
         */
        List<Jira> findByJiraprojectsProjectIdAndJiraType(String projectId,String jiraType);


        /**
         * This JPA Query returns a list of Jiras filtered with a jiraType
         * @param jiraType - the type of jira
         */
        List<Jira> findByJiraType(String jiraType);


        /**
         * This JPA Query returns a list of Jiras mapped to a  with jiraAssignee with a jiraType
         * @param jiraAssignee - jiras would be mapped to an assignee
         * @param jiraType - the type of jira
         */
        List<Jira> findByJiraAssigneeAndJiraType(String jiraAssignee,String jiraType);


        /**
         * This JPA Query returns a list of Jiras
         * @param jiraId - jiraID
         */
        List<Jira> findByJiraepicJiraId(String jiraId);


        /**
         * This Query returns a Optional<Jiras>
         * @param jiraId - jiraID
         */
        Optional<Jira> findByJiraId(String jiraId);


        /**
         * This Query deletes a record from the table
         * @param jiraId - jiraID
         */
        void deleteByJiraId(String jiraId);


        /**
         * This JPA Query returns list of jiras,
         * under a project specific to an employee with a filter of type
         * @param employeeId - the employee to which the jira is mapped
         * @param projectId - the project to which the jira is mapped
         * @param jiraType - the type of jira [ TASK, EPIC ]
         */
        List<Jira> findByEmployeeEmployeeIdAndJiraprojectsProjectIdAndJiraType(String employeeId, String projectId,String jiraType);


        /**
         * This JPA Query returns the flag value
         * @param jiraId- the id of jira [ TASK, EPIC ]
         */
        @Query(value = "select flag from jira e where e.jiraid=:jiraId",nativeQuery = true)
        int getByJiraId(@Param("jiraId") String jiraId);


        /**
         * This JPA Query returns list of jiras,
         * under a project specific to an employee with a filter of type,
         * which are in not in the DONE state
         * @param employeeId - the employee to which the jira is mapped
         * @param projectId - the project to which the jira is mapped
         * @param jiraType - the type of jira [ TASK, EPIC ]
         */
        @Query(value = "select * from jira e where e.jirastatus='To-Do' or e.jirastatus='In Progress' ",nativeQuery = true)
        List<Jira> getByEmployeeEmployeeIdAndJiraprojectsProjectIdAndJiraType(String employeeId, String projectId, String jiraType);


        /**
         * This JPA Query returns list of jiraId's,
         * under a specific employee with a filter of type
         * @param employeeId - the employee to which the jira is mapped
         * @param jiraType - the type of jira [ TASK, EPIC ]
         * @param projectId - to which the jiras are mapped
         */
        @Query(value = "select jiraid from jira",nativeQuery = true)
        List<String> findByEmployeeEmployeeIdAndJiraTypeAndJiraprojectsProjectId(String employeeId,String jiraType,String projectId);


        /**
         * This JPA Query returns list of jiras that are not mapped to any sprints ,
         * under a project specific to an employee
         */
        @Query(value = "SELECT * FROM jira WHERE jiraid NOT IN(SELECT jira_id FROM sprint_jira);",nativeQuery = true)
        List<Jira> getUnAddedSprints();
}

