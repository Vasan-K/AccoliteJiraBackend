package com.jira.AccoliteJiraBackend.Service;


import com.jira.AccoliteJiraBackend.Base.Sprint;
import com.jira.AccoliteJiraBackend.Exceptions.NoObjectException;
import com.jira.AccoliteJiraBackend.Repository.ProjectRepository;
import com.jira.AccoliteJiraBackend.Repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private ProjectRepository projectRepository;



    /**
     * performs adding a sprint to the database
     * by default when a sprint is created , it is active
     * a sprint is mapped to a project
     * @param st - sprint object(JSON Form)
     */
    public Sprint addSprints(Sprint st) {
        st.setCurrent(true);
        st.setSprintOfProject(projectRepository.getByProjectId(st.getSprintOfProject().getProjectId()));
        return sprintRepository.save(st);
    }


    /**
     * it returns the sprint object by ID
     * @param sprintId - type String
     */
    public Sprint viewSprint(String sprintId) throws NoObjectException {

        Optional<Sprint> sprintObj = this.sprintRepository.findBySprintId(sprintId);

        if (sprintObj.isPresent()) {
            return sprintObj.get();
        } else {
            throw new NoObjectException("No Such Sprints Found");
        }
    }


    /**
     * it returns all the sprints
     * findAll() returns a list
     */
    public List<Sprint> viewSprints() {

        return this.sprintRepository.findAll();
    }


    /**
     * it updates all the fields in a sprint
     * @param sprintId - ID to update that specific sprint
     * @param sprint - sprint object (JSON Form)
     */
    public void updateSprintById(String sprintId, Sprint sprint){
        sprint.setCurrent(true);
        sprintRepository.save(sprint);
    }


}

