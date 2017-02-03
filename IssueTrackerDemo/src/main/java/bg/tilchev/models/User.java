package bg.tilchev.models;

//import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//@Entity // couldn't get JPA annotations to work with struts 1
//@Table(name = "users")
public class User implements Serializable {

    private Long userId;
    private String name;
    private Set<Issue> issues;

    public User() {
        super();
    }

    public User(String name) {
        this.setName(name);
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

//    @Column(length = 50, nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Issue> getIssues() {
        if (this.issues == null) {
            this.setIssues(new HashSet<Issue>());
        }
        return this.issues;
    }

    public void addIssue(Issue issue) {
        this.getIssues().add(issue);
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }
}
