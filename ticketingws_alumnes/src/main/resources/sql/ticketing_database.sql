CREATE DATABASE ticketingdb CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE ticketingdb;
CREATE USER 'dbtuser'@'localhost' IDENTIFIED VIA mysql_native_password USING 'marianao';GRANT USAGE ON *.* TO 'dbtuser'@'localhost' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;GRANT ALL PRIVILEGES ON ticketingdb.* TO 'dbtuser'@'localhost'; 
