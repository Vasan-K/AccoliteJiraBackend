package com.jira.AccoliteJiraBackend.Repository;

import com.jira.AccoliteJiraBackend.Base.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {



      /**
       * This JPA Query returns the employee Object
       * @param employeeId - the employee to which the jira is mapped
       */
      Employee findByEmployeeId(String employeeId);


      /**
       * This JPA Query deletes a record from the database
       * @param employeeId - the employee to which the jira is mapped
       */
      void deleteByEmployeeId(String employeeId);


      /**
       * This JPA Query returns Optional<EmployeeObject>,
       * @param employeeId - the employee to which the jira is mapped
       */
      Optional<Employee> getByEmployeeId(String employeeId);


      /**
       * This JPA Query returns list of Employees,
       * under a specific project
       * @param projectId - the project to which the jira is mapped
       */
      List<Employee> findByProjectsProjectId(@Param("projectid") String projectId);


      /**
       * This JPA Query returns list of Employee Aliases,
       */
      @Query(value = "SELECT alias FROM Employee",nativeQuery = true)
      List<String> findByAlias();


      /**
       * This JPA Query returns an Employee Object
       * searches the Db via the employeeMail
       * @param email - the Employee mail
       */
      @Query("select e from Employee e where e.email=:email")
      Employee getEmployeeByEmail(@Param("email") String email);


      /**
       * This JPA Query returns an Employee Object
       * filters the search with alias
       * @param alias - the employee alias
       */
      @Query("select e from Employee e where e.alias=:alias")
      Employee getEmployeeByAlias(@Param("alias") String alias);


      /**
       * This JPA Query returns list of aliases(Strings)
       * @param level - the level/designation of the employee
       */
      @Query(value = "select alias from employee e where e.level=:level",nativeQuery = true)
      List<String> findByLevel(@Param("level") String level);
}
