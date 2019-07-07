package ru.zhivenkov.rest_service.repository;


import ru.zhivenkov.rest_service.entity.News;
import ru.zhivenkov.rest_service.entity.User;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.*;
import java.util.List;
import java.util.logging.Logger;


/*
когда нужно иметь только один экземпляр объекта, использовать @ApplicationScoped - такой объект проксирован и, следовательно, даже может быть правильно сериализован из коробки. Альтернатива @Singleton, который является превдообъектом и не проксирован
Есть еще одна разница: @Singleton не bean определяет аннотации, поскольку область Singleton не является нормальной областью. Тогда @ApplicationScoped является bean, определяющим аннотации.
 */
@ApplicationScoped
public class NewsRepository {
    // Inject является частью Java CDI (Contexts and Dependency Injection)
    @Inject
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Resource
    private UserTransaction userTransaction;

    public List<News> getAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();  // строить объекты запросов
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> newsRoot = criteriaQuery.from(News.class);
        criteriaQuery.select(newsRoot);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public News getById(long id){
        logger.info("Get detail news by id: " + id);
        return entityManager.find(News.class, id);
    }

    public void deleteById(News news) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        logger.info("Delete news by : " + news.toString());
        userTransaction.begin();
        entityManager.remove(entityManager.contains(news) ? news : entityManager.merge(news));
        userTransaction.commit();
    }

    public News addNews(News news) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        logger.info("Add news : " + news.toString());
        userTransaction.begin();
        entityManager.merge(news);
        userTransaction.commit();
        return news;
    }

    public List<News> getFromUser(User creator_id){
        logger.info("Get newses from : " + creator_id);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> element = criteriaQuery.from(News.class);
        criteriaQuery.select(element).where(criteriaBuilder.equal(element.get("creator_id"), creator_id));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
