<<<<<<< HEAD
# lux

* **DB** - the first task, **WEB** - the second one.
* Both, **WEB** and **DB** apps are using the same DB settings:
```sh
db.driver=com.mysql.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/lux
db.username=root
db.password=root
```
* In case you has not created a database, you can fulfil init_script.sql:
```sh
CREATE DATABASE IF NOT EXISTS LUX;

create table if not exists FILE_INFO (
   file_id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(200) NOT NULL,
   lines_number BIGINT NOT NULL,
   PRIMARY KEY (file_id)
);
 
create table if not exists LINE_INFO (
   line_id BIGINT NOT NULL AUTO_INCREMENT,
   file_id BIGINT NOT NULL,
   line VARCHAR(30) NOT NULL,
   longest_word  VARCHAR(30) NOT NULL,
   shortest_word VARCHAR(30) NOT NULL,
   average_word_length DECIMAL(3,2) NOT NULL,
   PRIMARY KEY (line_id),
   CONSTRAINT file_unive FOREIGN KEY (file_id) REFERENCES FILE_INFO (file_id) ON UPDATE CASCADE ON DELETE CASCADE
);
```

* **DB** app can scan both single file and directory (along with subdirectories).
* **DB** app works concurrently.
* **WEB** app can filter files by name.
* In order to view frontend of **WEB** app, please launch the server with the app and open `http://localhost:8080/luxviewer/`
=======
Example of Spring Data JPA application (Hibernate used as implementation of JPA).

You can read tutorial for this app by following link: http://fruzenshtein.com/spring-mvc-hibernate-maven-crud/
>>>>>>> cf9abf76e7ed21d77426e058f33398eda74e38f9
