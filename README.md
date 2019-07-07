# java_rest_api 
rest сервис для новостного модуля
•        Получение списка новостей (данные: id, title,content,creation_date, fio (фио создателя новости ))
•        Получение деталей новости (данные: id, title,content,creation_date, fio (фио создателя новости ))
•        Создание новости (данные: id, title,content,creation_date, creator_id (id создателя новости ))
•        Удаление новости 
 
Сущность новость описывается в БД полями:
Id
Title
Content
Creation_date
creator_id (это ссылка на связную таблицу – справочник юзеров)
 
Сущность «Справочник юзеров» описывается в БД полями:
Id
Fio
 
Для разработки сервера использоваись следующие инструменты:
1.      Java
2.      Spring
3.      Hibernate
4.      БД postgres
Протокола взаимодействия фронта и бэкенда – json

