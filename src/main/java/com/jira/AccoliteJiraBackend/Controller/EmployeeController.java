package com.jira.AccoliteJiraBackend.Controller;

import com.jira.AccoliteJiraBackend.Base.Credentials;
import com.jira.AccoliteJiraBackend.Base.Employee;
import com.jira.AccoliteJiraBackend.BusinessLogic.EmployeeComponent;
import com.jira.AccoliteJiraBackend.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("")
@RequestMapping(value = PathConstants.empPath)
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @Autowired
    public EmployeeComponent employeeComponent;

    @GetMapping(value = PathConstants.getAllEmpPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getAllEmployees(){
           try{
               return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(),HttpStatus.OK);
           } catch (Exception e){
               return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
           }
    }

    @GetMapping(value = PathConstants.getEmpDropDownPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getEmployeesDropDown(){
        return new ResponseEntity<>(employeeService.getEmployeesDropDown(),HttpStatus.OK);
    }

    @GetMapping(value = PathConstants.findEmpByIdPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee findEmployeeById(@PathVariable("employeeId") String employeeId){
           return employeeService.findEmployeeById(employeeId);
    }

    @GetMapping(value = PathConstants.getEmpByProjectPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getAllEmployeesByProjectId(@PathVariable("projectId") String projectId){
         try{
             return new ResponseEntity<List<Employee>>(employeeService.getAllEmployeesByProjectId(projectId),HttpStatus.OK);
         } catch (Exception e){
              return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @PostMapping(value = PathConstants.addEmpPath,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
    {
        try {
            employeeService.createEmployee(employee);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = PathConstants.deleteEmpPath,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public void deleteEmployeeById(@PathVariable("employeeId") String employeeId){
           employeeService.deleteEmployeeById(employeeId);
    }

    @PostMapping(value = PathConstants.loginPath,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String checkCredentials(@RequestBody Credentials credentials)
    {
        return employeeComponent.checkCredentials(credentials);
    }

    @GetMapping(value = PathConstants.findEmpByAliasPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee findEmployeeByAlias(@PathVariable("employeeAlias") String employeeAlias){
        return employeeComponent.findEmployeeByAlias(employeeAlias);
    }

    @GetMapping(value = PathConstants.findEmpByLevelPath,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getReportersDropDown(){
        return employeeService.getReportersDropDown();
    }




}
