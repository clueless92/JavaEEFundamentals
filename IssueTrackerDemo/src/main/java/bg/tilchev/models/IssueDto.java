package bg.tilchev.models;

public class IssueDto {

    private String name;
    private String project;
    private String priority;
    private String type;
    private String state;
    private Integer sprint;
    private Integer storyPoints;
    private Integer assigneeCount;

    public IssueDto() {
        super();
    }

    public IssueDto(String name) {
        this.name = name;
    }

    public IssueDto(String name, Integer assigneeCount) {
        this.name = name;
        this.assigneeCount = assigneeCount;
    }

    public IssueDto(String name, String project, String priority, String type, String state, Integer sprint, Integer
            storyPoints, Integer assigneeCount) {
        this.name = name;
        this.project = project;
        this.priority = priority;
        this.type = type;
        this.state = state;
        this.sprint = sprint;
        this.storyPoints = storyPoints;
        this.assigneeCount = assigneeCount;
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

    public Integer getAssigneeCount() {
        return this.assigneeCount;
    }

    public void setAssigneeCount(Integer assigneeCount) {
        this.assigneeCount = assigneeCount;
    }
}
