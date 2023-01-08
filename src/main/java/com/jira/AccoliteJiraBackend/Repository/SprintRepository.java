package com.jira.AccoliteJiraBackend.Repository;


import com.jira.AccoliteJiraBackend.Base.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface SprintRepository extends JpaRepository<Sprint,String> {


    /**
     * This Custom Query returns the sprintId of the sprint which is mapped to the projectId
     * @param projectId - String datatype
     */
    @Query(value = "SELECT sprintid FROM sprints t WHERE t.iscurrent=true",nativeQuery = true)
    String findBySprintOfProjectProjectId(String projectId);


    /**
     * This JPA Query returns an Optional<Sprint>[SprintObject]
     * @param sprintId - String datatype
     */
    Optional<Sprint> findBySprintId(String sprintId);

}
