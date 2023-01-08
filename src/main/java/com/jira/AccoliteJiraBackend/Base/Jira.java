package com.jira.AccoliteJiraBackend.Base;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;



@Getter
@Setter
@Entity
@Table(name="jira")
public class Jira {

         @Id
         @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jira_seq")
         @GenericGenerator(
                 name = "jira_seq",
                 strategy = "com.jira.AccoliteJiraBackend.Base.StringPrefixedSequenceIdGenerator",
                 parameters = {
                         @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                         @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "J"),
                         @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%01d") })
         private String jiraId;

         @Column(name="projectname")
         @NotNull
         private String projectName;

         @Column(name="jirastatus")
         @NotNull
         private String jiraStatus;

         @Column(name="jiratitle")
         @NotNull
         private String jiraTitle;

         @Column(name="jiradescription")
         @NotNull
         private String jiraDescription;

         @Column(name="jiraassignee",nullable = true)
         private String jiraAssignee;

         @Column(name="jirareporter")
         @NotNull
         private String jiraReporter;

         @Column(name="jiratype")
         @NotNull
         private String jiraType;

         @Column(name="jirapriority")
         @NotNull
         private int jiraPriority;

         @Column(name="jiralinkedissue")
         private String jiraLinkedIssue;

         @Column(name="activejira")
         @NotNull
         private boolean isjiraActive;

         @Column(name="flag")
         @NotNull
         private int flag;

         @ManyToOne
         @JoinColumn(name="epictasksid", referencedColumnName = "jiraid")
         private Jira jiraepic;

         @OneToMany(mappedBy = "jiraepic",fetch = FetchType.EAGER)
         @JsonIgnore
         private List<Jira> JiraTasks;

         @ManyToOne
         @JoinColumn(name="jiraproject_id", referencedColumnName = "projectid")
         private Project jiraprojects;

         @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "jirasOfSprint")
         private List<Sprint> sprintOfJiras;

         @ManyToOne
         @JoinColumn(name = "jira_employee_id",referencedColumnName = "employeeid")
         private Employee employee;





}
