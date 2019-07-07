package ru.zhivenkov.rest_service.rest;

import ru.zhivenkov.rest_service.entity.News;

import ru.zhivenkov.rest_service.repository.NewsRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.*;
import javax.transaction.NotSupportedException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Path("/news") // путь по которому будут доступны запросы
public class NewsREST {
    @Inject
    private Logger logger;


    @Inject
    private NewsRepository newsRepository;

    // Получение списка новостей. Путь /
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getAll(){
        logger.info("Get all newses");
        return newsRepository.getAll();
    }

    // Получение деталей новости. Путь /{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News getById(@PathParam("id") long id){
        logger.info("Get news by id :" + id);
        return newsRepository.getById(id);
    }

    // Удаление новости
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response deleteById(@PathParam("id") long id) throws HeuristicRollbackException, HeuristicMixedException, NotSupportedException, RollbackException, SystemException {
        logger.info("Delete news by id :" + id);
        newsRepository.deleteById(newsRepository.getById(id));
        return Response.status(200).entity("News is deleted by id:" + id).build();
    }

    // Создание/добавление новости
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public News addNews(News news) throws HeuristicRollbackException, HeuristicMixedException, NotSupportedException, RollbackException, SystemException {
        logger.info("Add news  :" + news.toString());
        return newsRepository.addNews(news);
    }



}
