package bg.tilchev.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.tilchev.entity.Post;

@Stateless
public class PostServiceImpl implements PostService {

    @PersistenceContext
    protected EntityManager entityManager;
	
	@Override
	public Post save(Post entity) {
		entityManager.persist(entity);
		return entity;
	}

}
