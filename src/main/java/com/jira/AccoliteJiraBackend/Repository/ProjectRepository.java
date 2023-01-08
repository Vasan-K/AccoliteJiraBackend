package com.jira.AccoliteJiraBackend.Repository;


import com.jira.AccoliteJiraBackend.Base.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface ProjectRepository extends JpaRepository<Project,String> {


    /**
     * This JPA Query returns the projectObject
     * @param projectId - String datatype
     */
    Project getByProjectId(String projectId);


    /**
     * This JPA Query returns an Optional<Project>[projectObject]
     * @param projectId - String datatype
     */
    Optional<Project> queryByProjectId(String projectId);


    /**
     * This JPA Query returns the projectId upon passing the projectName
     * @param projectLabel - String datatype
     */
    @Query(value = "SELECT projectid FROM Project t WHERE t.projectLabel=?1 ",nativeQuery = true)
    String findByProjectId(String projectLabel);


    /**
     * This JPA Query returns an List<projectNames>
     */
    @Query(value = "SELECT projectLabel FROM Project",nativeQuery = true)
    List<String> findByProjectLabel();


    /**
     * This JPA Query returns a Set of projects under an employee
     * @param alias - Employee Alias
     */
    Set<Project> findByEmployeesAlias(String alias);
}
