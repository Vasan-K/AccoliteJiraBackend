package com.jira.AccoliteJiraBackend.BusinessLogic;

import com.jira.AccoliteJiraBackend.Base.BCrypt;
import com.jira.AccoliteJiraBackend.Base.Credentials;
import com.jira.AccoliteJiraBackend.Base.Employee;
import com.jira.AccoliteJiraBackend.Repository.EmployeeRepository;
import com.jira.AccoliteJiraBackend.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeComponent {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * Performs basic authentication with BCrypt
     * it verifies with the email and password in the database
     * BCrypt.checkpw() takes in the user input password checks it with
     * existing password
     * @param credentials - whole object
     */
    public String checkCredentials(Credentials credentials)
    {
        Employee employee=employeeRepository.getEmployeeByEmail(credentials.getEmail());
        String inputPassword = credentials.getPassword();
        if(employee==null)
        {
            throw new IllegalStateException("Employee with that email not present ");
        }
        String originalPassword = employee.getPassword();
        boolean matched = BCrypt.checkpw(inputPassword,originalPassword);
        if(matched){
                return "Successfully Login";
        }
        else
                throw new IllegalStateException("Password Incorrect");

        }


    /**
     * It returns the employee object
     * it runs a query that finds the employee by alias
     * @param alias - string datatype
     */
    public Employee findEmployeeByAlias(String alias) {

        return employeeRepository.getEmployeeByAlias(alias);
    }

}
