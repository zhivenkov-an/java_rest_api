package ru.zhivenkov.rest_service.repository;

import ru.zhivenkov.rest_service.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UserRepository {
    @Inject
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    public User getById(long id){
        logger.info("Geted user by id: " + id);
        return entityManager.find(User.class,id);
    }

    public List<User> getAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        return  entityManager.createQuery(criteriaQuery).getResultList();
    }


}
