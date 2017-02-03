package bg.tilchev.forms;

import org.apache.struts.action.ActionForm;

public class IssueForm extends ActionForm {

    private String dispatch;
    private String name;
    private String project;
    private String priority;
    private String type;
    private String state;
    private Integer sprint;
    private Integer storyPoints;

    public IssueForm() {
        super();
    }

    public String getName() {
        return name;
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

    public String getDispatch() {
        return this.dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public void reset() {
        this.setName(null);
        this.setPriority(null);
        this.setState(null);
        this.setStoryPoints(null);
        this.setProject(null);
        this.setSprint(null);
        this.setType(null);
    }
}
