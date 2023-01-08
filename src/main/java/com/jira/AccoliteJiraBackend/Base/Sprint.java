package com.jira.AccoliteJiraBackend.Base;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.*;




@Entity
@Getter
@Setter
@Component
@Table(name="sprints")
public class Sprint {

         @Id
         @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sprint_seq")
         @GenericGenerator(
                 name = "sprint_seq",
                 strategy = "com.jira.AccoliteJiraBackend.Base.StringPrefixedSequenceIdGenerator",
                 parameters = {
                         @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                         @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "SPR"),
                         @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%01d") })
         @Column(name="sprintid")
         private String sprintId;

         @Column(name="projectname")
         @NotNull
         private String projectName;

         @Column(name="allottedtime")
         @NotNull
         private int allottedTime;

         @NotNull
         @Column(name="sprintdescription")
         private String sprintDescription;

         @Column(name="startdate")
         @NotNull
         @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
         private Date startDate;

         @Column(name="enddate")
         @NotNull
         @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
         private Date endDate;

         @Column(name="iscurrent")
         @NotNull
         private boolean isCurrent;

         @ManyToOne
         @JoinColumn(name="projectsprintid",referencedColumnName = "projectid")
         private Project sprintOfProject;

         @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
         @JoinTable(
                  name="sprint_jira",
                  joinColumns = @JoinColumn(name="sprint_id"),
                  inverseJoinColumns = @JoinColumn(name="jira_id")
         )
         @JsonBackReference
         private List<Jira> jirasOfSprint;





}
