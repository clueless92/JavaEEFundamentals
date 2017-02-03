package bg.tilchev.models;

import java.util.HashSet;
import java.util.Set;

public class Issue {

    private Long issueId;
    private String name;
    private String project;
    private String priority;
    private String type;
    private String state;
    private Integer sprint;
    private Integer storyPoints;
    private Set<User> assignees;

    public Issue() {
        super();
    }

    public Issue(String name) {
        this.name = name;
    }

    public Long getIssueId() {
        return this.issueId;
    }

    public void setIssueId(Long id) {
        this.issueId = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getSprint() {
        return this.sprint;
    }

    public void setSprint(Integer sprint) {
        this.sprint = sprint;
    }

    public Integer getStoryPoints() {
        return this.storyPoints;
    }

    public void setStoryPoints(Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    public Set<User> getAssignees() {
        if (this.assignees == null) {
            this.setAssignees(new HashSet<User>());
        }
        return this.assignees;
    }

    public void addAssignee(User assignee) {
        this.getAssignees().add(assignee);
    }

    public void setAssignees(Set<User> assignees) {
        this.assignees = assignees;
    }
}
