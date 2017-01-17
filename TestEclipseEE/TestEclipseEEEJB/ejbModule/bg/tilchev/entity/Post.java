package bg.tilchev.entity;

import java.util.Date;
import javax.persistence.*;

import bg.tilchev.entity.base.BaseDomainObject;

@Entity
@Table(name = "posts")
public class Post extends BaseDomainObject {
	
    private static final long serialVersionUID = 1L;
    
    private String title;
    private String content;
    private Date date;
    private User author;
    
    public Post() {
    	super();
    }

    @Column(name = "title", length = 100, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content", length = 4000, nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
