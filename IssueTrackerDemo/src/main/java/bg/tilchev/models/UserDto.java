package bg.tilchev.models;

import java.io.Serializable;

public class UserDto implements Serializable {

    private String name;
    private Integer issueCount;

    public UserDto() {
        super();
    }

    public UserDto(String name) {
        this.name = name;
    }

    public UserDto(String name, Integer issueCount) {
        this.name = name;
        this.issueCount = issueCount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIssueCount() {
        return this.issueCount;
    }

    public void setIssueCount(Integer issueCount) {
        this.issueCount = issueCount;
    }
}
