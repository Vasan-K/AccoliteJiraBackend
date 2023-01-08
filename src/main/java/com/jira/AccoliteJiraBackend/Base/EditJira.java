package com.jira.AccoliteJiraBackend.Base;


import lombok.Data;

import java.util.Objects;

@Data
public class EditJira {

    private String jiraStatusUpdate;
    private String jiraDescriptionUpdate;
    private String jiraReporterUpdate;
    private int jiraPriorityUpdate;
    private String jiraLinkedIssueUpdate;

    private Employee employeeUpdate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditJira editJira = (EditJira) o;
        return jiraPriorityUpdate == editJira.jiraPriorityUpdate &&  jiraStatusUpdate.equals(editJira.jiraStatusUpdate) && jiraDescriptionUpdate.equals(editJira.jiraDescriptionUpdate) && jiraReporterUpdate.equals(editJira.jiraReporterUpdate) && jiraLinkedIssueUpdate.equals(editJira.jiraLinkedIssueUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jiraStatusUpdate, jiraDescriptionUpdate, jiraReporterUpdate, jiraPriorityUpdate, jiraLinkedIssueUpdate);
    }
}
