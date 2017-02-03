package bg.tilchev.repos;

import bg.tilchev.hibernate.HibernateUtil;
import bg.tilchev.models.User;
import bg.tilchev.models.UserDto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepo {

    private Session session;

    public UserRepoImpl() {
        super();
    }

    public boolean persist(UserDto user) {
        User entity = new User(user.getName());
        return this.persist(entity);
    }

    public boolean persist(User user) {
        Session session = this.getSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByExactName(String name) {
        Session session = this.getSession();
        User user;
        try {
            session.beginTransaction();
            Criteria query = session.createCriteria(User.class);
            query.add(Restrictions.like("name", name, MatchMode.EXACT));
            List<User> users = query.list();
            user = users.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<UserDto> getUsersByName(String name) {
        Session session = this.getSession();
        List<UserDto> users = new ArrayList<UserDto>();
        try {
            session.beginTransaction();
            Criteria query = session.createCriteria(User.class);
            query.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
            List<User> list = query.list();
            for (User user : list) {
                String username = user.getName();
                int issueCount = user.getIssues().size();
                users.add(new UserDto(username, issueCount));
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        return users;
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
