package com.jira.AccoliteJiraBackend.Base;

import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Component
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @GenericGenerator(
            name = "employee_seq",
            strategy = "com.jira.AccoliteJiraBackend.Base.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ACC"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%01d") })
    @Column(name = "employeeid")
    private String employeeId;

    @NotNull
    @Column(name = "alias")
    private String alias;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "level")
    private String level;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "employees")
    private Set<Project> projects ;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Jira> jiraOfEmployee;

}

