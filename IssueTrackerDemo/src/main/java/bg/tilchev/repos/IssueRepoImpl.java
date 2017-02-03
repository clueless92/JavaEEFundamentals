package bg.tilchev.repos;

import bg.tilchev.hibernate.HibernateUtil;
import bg.tilchev.models.Issue;
import bg.tilchev.models.IssueDto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class IssueRepoImpl implements IssueRepo {

    private Session session;

    public IssueRepoImpl() {
        super();
    }

    public boolean persist(IssueDto issue) {
        Issue entity = new Issue(issue.getName());
        entity.setPriority(issue.getPriority());
        entity.setProject(issue.getProject());
        entity.setSprint(issue.getSprint());
        entity.setState(issue.getState());
        entity.setType(issue.getType());
        entity.setStoryPoints(issue.getStoryPoints());
        return this.persist(entity);
    }

    public boolean persist(Issue issue) {
        Session session = this.getSession();
        try {
            session.beginTransaction();
            session.save(issue);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<IssueDto> getIssuesByName(String name) {
        Session session = this.getSession();
        List<IssueDto> issues = new ArrayList<IssueDto>();
        try {
            session.beginTransaction();
            Criteria query = session.createCriteria(Issue.class);
            query.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
            List<Issue> list = query.list();
            for (Issue issue : list) {
                String issueName = issue.getName();
                int assigneeCount = issue.getAssignees().size();
                IssueDto issueDto = new IssueDto(issueName, assigneeCount);
                issueDto.setPriority(issue.getPriority());
                issueDto.setState(issue.getState());
                issueDto.setProject(issue.getProject());
                issueDto.setType(issue.getType());
                issueDto.setSprint(issue.getSprint());
                issueDto.setStoryPoints(issue.getStoryPoints());
                issues.add(issueDto);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        return issues;
    }

    @SuppressWarnings("unchecked")
    public Issue getIssueByExactName(String name) {
        Session session = this.getSession();
        Issue issue;
        try {
            session.beginTransaction();
            Criteria query = session.createCriteria(Issue.class);
            query.add(Restrictions.like("name", name, MatchMode.EXACT));
            List<Issue> issues = query.list();
            issue = issues.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        return issue;
    }

    public void closeConnection() {
        this.session.close();
//        this.session = null;
    }

    private Session getSession() {
        if (this.session == null || !this.session.isOpen()) {
            this.session = HibernateUtil.getSessionFactory().openSession();
        }
        return this.session;
    }
}
