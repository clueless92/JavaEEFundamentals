package bg.tilchev.entity;

import java.util.List;
import javax.persistence.*;
import bg.tilchev.entity.base.BaseDomainObject;

@Entity
@Table(name = "users")
public class User extends BaseDomainObject {
	
	private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<Post> posts;
    private Long postsCount;

    public User() {
    }

    public User(Long id, String username, String password, String first, String last, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = first;
        this.lastName = last;
        this.email = email;
    }
    
    public User(Long id, String username, String password, String first, String last, String email, Long postsCount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.postsCount = postsCount;
    }

    @Column(name = "username", length = 45, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", length = 200, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "first_name", length = 45, nullable = true)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = 45, nullable = true)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email", length = 45, nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", fetch = FetchType.LAZY)
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

	@Transient
	public Long getPostsCount() {
		return this.postsCount;
	}

	public void setPostsCount(Long postsCount) {
		this.postsCount = postsCount;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Users[ id=" + id + " ]";
    }
}
