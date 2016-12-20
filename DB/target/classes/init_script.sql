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