package bg.tilchev.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

import bg.tilchev.entity.User;

/**
 * Session Bean implementation class UserServiceImpl
 */
@Stateless
public class UserServiceImpl implements UserService {

    @PersistenceContext
    protected EntityManager entityManager;

    public UserServiceImpl() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAllUsers() {
        String query = "SELECT u"
        			 + "  FROM User u"
        			 + " ORDER BY upper(u.username) ASC";
        Query q = entityManager.createQuery(query);

        return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAllUsersAndPostsCount() {
        String query = "SELECT new User(u.id, u.username, u.password, u.firstName, u.lastName, u.email, COUNT(p))"
        			 + "  FROM User u "
        			 + "  LEFT JOIN u.posts p"
        			 + " GROUP BY u.id"
        			 + " ORDER BY UPPER(u.username) ASC";
        Query q = entityManager.createQuery(query);

        return q.getResultList();
    }

    @Override
    public User save(User entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public User update(User entity) {
        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public void delete(User entity) {
        entityManager.remove(entity);
    }

    @Override
    public User findById(Long id) {
        try {
        	User instance = entityManager.find(User.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public User loginUser(String aUsername, String aPassword) {
        StringBuffer query = new StringBuffer(
                "SELECT u"
              + "  FROM User u"
              + " WHERE u.username = :em"
              + "   AND u.password = :p");

        Query q = entityManager.createQuery(query.toString());
        q.setParameter("em", aUsername);
        q.setParameter("p", aPassword);

        try {
            return (User) q.getSingleResult();
        } catch (NoResultException nre) {
            // the user doesn't exist
            return null;
        }
    }

    @Override
    public User checkUserExists(String username, Long id) {
        StringBuffer query = new StringBuffer(
                "SELECT u"
              + "  FROM User u"
              + " WHERE UPPER(u.username) = UPPER(:em)");

        if (id != null) {
            query.append(" AND u.id <> ").append(id);
        }

        Query q = entityManager.createQuery(query.toString());
        q.setParameter("em", username);

        try {
            return (User) q.getSingleResult();
        } catch (NoResultException nre) {
            // the user doesn't exist
            return null;
        }
    }
}