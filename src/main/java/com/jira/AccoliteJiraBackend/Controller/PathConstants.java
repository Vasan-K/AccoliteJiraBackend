package com.jira.AccoliteJiraBackend.Controller;

public final class PathConstants {

    private PathConstants() {
        throw new AssertionError("No instances for you!");
    }

    public static final String empPath = "/employees";
    public static final String getAllEmpPath = "/getallemployees";
    public static final String getEmpDropDownPath = "/getemployeedropdown";
    public static final String findEmpByIdPath = "/getemployee/{employeeId}";
    public static final String getEmpByProjectPath = "/projectemployees/{projectId}/allemployees";
    public static final String addEmpPath = "/employee";
    public static final String deleteEmpPath = "/delete/{employeeId}";
    public static final String loginPath = "/check";
    public static final String findEmpByAliasPath = "/getemployeeByAlias/{employeeAlias}";
    public static final String findEmpByLevelPath ="/getemployeeByLevel";

    public static final String jiraPath = "/jira";
    public static final String getAllJirasPath ="/alljiras";
    public static final String getProjectIdPath = "/jiraprojectid/{projectLabel}";
    public static final String getJiraPath = "/jirabyid/{jiraId}";
    public static final String addJiraPath ="/addjira";
    public static final String updateJiraPath ="/updateJira/{jiraId}";
    public static final String deleteJiraPath ="/deletejira/{jiraId}";
    public static final String allEpicsOfProjectPath = "/getEpics/{projectId}";
    public static final String allTasksPath ="/getAllTasks";
    public static final String allEpicPath ="/getAllEpics";
    public static final String jirasOfEmpPath ="/getAllJirasOfEmployee/{jiraAssignee}";
    public static final String epicsOfEmpPath ="/getAllEpicsOfEmployee/{jiraAssignee}";
    public static final String epicStatusPath ="/changeEpicStatus/{jiraId}";
    public static final String epicEmpPath ="/getEpicOfEmployee/{employeeId}/project/{projectId}";
    public static final String taskEmpPath="/getTaskOfEmployee/{employeeId}/project/{projectId}";
    public static final String taskEmpNotDonePath="/getTaskOfEmployeeNotDone/{employeeId}/project/{projectId}";
    public static final String projectIdJiraIdPath="/getProjectId/project/{jiraId}";
    public static final String tasksOfEpicPath="/gettasksofepic/{jiraId}";
    public static final String viewSprintUnAddedTasksPath="/unAddedTasks/{projectId}/sprint/{employeeId}";

    public static final String projectPath = "/projects";
    public static final String viewAllProjectPath ="/viewProject";
    public static final String viewProjectByIdPath ="/viewProjectbyId/{projectId}";
    public static final String getProjectDropDownPath ="/projectdropdown";
    public static final String addProjectPath ="/project";
    public static final String addEmpProjectPath ="/add/employee/{projectId}/project/{employeeId}";
    public static final String deleteProjectPath ="/remove/employee/{projectId}/project/{employeeId}";
    public static final String projectbyAliasPath ="/viewProjectbyAlias/{alias}";

    public static final String sprintPath ="/sprint";
    public static final String addSprintPath ="/addSprint";
    public static final String viewAllSprintPath ="/viewSprint";
    public static final String viewSprintPath ="/{sprintId}";
    public static final String viewCurrentSprintPath ="/currentsprint/{projectId}";
    public static final String addJiraSprintPath ="/{sprintId}/jiras/{jiraId}";
    public static final String addSprintProjectPath ="/{sprintId}/projects/{projectId}";
    public static final String viewSprintTaskPath="/sprinttasks/{sprintId}";


}
