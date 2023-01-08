package com.jira.AccoliteJiraBackend.Service;

import com.jira.AccoliteJiraBackend.Base.*;
import com.jira.AccoliteJiraBackend.Exceptions.NoObjectException;
import com.jira.AccoliteJiraBackend.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * Performs adding an employee to the database
     * it saves the password in hashed-out form for security purposes
     * BCrypt.hashpw takes in the password and hashes it and that is being saved
     * @param employee - whole object
     */
    public void createEmployee(Employee employee) {

        String employeeEmail = employee.getEmail();
        boolean isValid = employeeEmail.contains("accolitedigital.com");
        if(isValid){
            String originalPassword = employee.getPassword();
            String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
            employee.setPassword(generatedSecuredPasswordHash);
            employeeRepository.save(employee);
        } else {
            try {
                throw new NoObjectException("Enter the Correct Email");
            } catch (NoObjectException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Performs finding an employee by ID
     * it runs a query and returns the whole employee object
     * @param employeeId - the id in string format
     */
    public Employee findEmployeeById(String employeeId) {

          return employeeRepository.findByEmployeeId(employeeId);
    }

    /**
     * Performs deleting an employee from the database
     * it runs a query and deletes the whole employee object
     * @param employeeId - the id in string format
     */
    public void deleteEmployeeById(String employeeId){

          employeeRepository.deleteByEmployeeId(employeeId);
    }

    /**
     * It returns all the employees in a specific project
     * it runs a custom query which returns list of employee objects
     * @param projectId - it takes in projectId as parameter and
     * runs through the query. returns the list of employees
     */
    public List<Employee> getAllEmployeesByProjectId(String projectId){

        return employeeRepository.findByProjectsProjectId(projectId);
    }

    /**
     * it returns all the employees
     * findAll() method returns the list.
     */
    public List<Employee> getAllEmployees(){

        return employeeRepository.findAll();
    }

    /**
     * it returns all the employees by alias
     * it runs a query and returns a list of strings
     */
    public List<String> getEmployeesDropDown(){

         return employeeRepository.findByAlias();
    }

    /**
     * it returns all the employees by level
     * it runs a query and returns a list of strings
     */
    public List<String> getReportersDropDown(){

         return employeeRepository.findByLevel("Manager");
    }

}
