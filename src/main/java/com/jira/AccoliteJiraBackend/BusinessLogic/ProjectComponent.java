package com.jira.AccoliteJiraBackend.BusinessLogic;

import com.jira.AccoliteJiraBackend.Base.Employee;
import com.jira.AccoliteJiraBackend.Base.Project;
import com.jira.AccoliteJiraBackend.Repository.EmployeeRepository;
import com.jira.AccoliteJiraBackend.Repository.ProjectRepository;
import com.jira.AccoliteJiraBackend.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProjectComponent {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * Performs removing an employee from a project
     * @param projectId - from which the employee has to be removed
     * @param employeeId - the employee which needs to be removed
     */
    public void removeEmployeeFromProject(String projectId, String employeeId) {

        Optional<Employee> employeeOptional=this.employeeRepository.getByEmployeeId(employeeId);
        Optional<Project> projectOptional=this.projectRepository.queryByProjectId(projectId);

        if(projectOptional.isPresent() && employeeOptional.isPresent())
        {

            Employee employee=employeeOptional.get();
            Project project=projectOptional.get();

            project.getEmployees().remove(employee);
            projectRepository.save(project);
        }
    }


    /**
     * It returns a list of projects that an employee holds
     * A Query is run that finds the projects of an employee
     * this is employee specific
     * @param alias - alias of the employee is being taken in
     */
    public Set<Project> viewAllProjectsByAlias(String alias){
        return this.projectRepository.findByEmployeesAlias(alias);
    }


    /**
     * Performs adding an employee to a project
     * @param projectId - to which the employee must be mapped
     * @param employeeId - the Id of the employee who must be added
     */
    public ResponseEntity<Project> addEmployeeToProject(String projectId, String employeeId) {

        Optional<Employee> employeeOptional=this.employeeRepository.getByEmployeeId(employeeId);
        Optional<Project> projectOptional=this.projectRepository.queryByProjectId(projectId);

        if(projectOptional.isPresent() && employeeOptional.isPresent())
        {

            Employee employee=employeeOptional.get();
            Project project=projectOptional.get();

            Set<Employee> s=project.getEmployees();

            project.getEmployees().add(employee);
            projectRepository.save(project);
            return new ResponseEntity<Project>(project, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


}
