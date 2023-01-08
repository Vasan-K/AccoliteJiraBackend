package com.jira.AccoliteJiraBackend.Base;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Component
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @GenericGenerator(
            name = "project_seq",
            strategy = "com.jira.AccoliteJiraBackend.Base.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PR"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%01d") })
    @Column(name="projectid")
    private String projectId;

    @NotNull
    @Column(name = "projectDescription")
    private String projectDescription;

    @NotNull
    @Column(name = "projectLabel")
    private String projectLabel;

    @NotNull
    @CreationTimestamp
    private Timestamp timestamp;

    @NotNull
    @Column(name = "isActive")
    private boolean isActive;


    @OneToMany(mappedBy = "jiraprojects")
    @JsonIgnore
    private List<Jira> jiras;

    @OneToMany(mappedBy = "sprintOfProject",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Sprint> sprints=new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade =  CascadeType.ALL )
    @JoinTable(
            name = "Employees_Projects",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "employee_id") }
    )
    private Set<Employee> employees = new HashSet<>();



}

