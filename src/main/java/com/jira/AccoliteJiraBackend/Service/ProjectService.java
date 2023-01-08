package com.jira.AccoliteJiraBackend.Service;


import com.jira.AccoliteJiraBackend.Base.Project;
import com.jira.AccoliteJiraBackend.Repository.EmployeeRepository;
import com.jira.AccoliteJiraBackend.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * performs adding a project to the database
     * upon creating a project , it will be active unless all its epics,tasks are done.
     * @param project - object (JSON Form)
     */
    public void createProject(Project project)
    {
        project.setActive(true);
        projectRepository.save(project);
    }

    /**
     * views all the projects
     * findAll() returns a list of project objects
     */
    public List<Project> viewAllProjects(){

        return projectRepository.findAll();
    }

    /**
     * it returns all the projects by projectLabel
     * it runs a query and returns a list of strings
     */
    public List<String> getProjectDropdown(){

          return projectRepository.findByProjectLabel();
    }

    /**
     * it returns the project by ID
     * @param projectId - type String
     */
    public Project viewProjectById(String projectId){

         return projectRepository.getByProjectId(projectId);
    }



}
