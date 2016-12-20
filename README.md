# lux

* DB - the first task, DB - the second one.
* Both, WEB and DB apps are using the same DB settings:
```sh
db.driver=com.mysql.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/lux
db.username=root
db.password=root
```
* In case you do not have created DB, you can fulfil init_script.sql:
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

* DB app can scan both single file and directory (along with subdirectories).
* DB app works concurrently.
* Web app can filter files by name.
