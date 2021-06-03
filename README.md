# Lemon Api

## Stack

- DOCKER
- mysql
- java

## Command

Execute the following command on the terminal:

```
docker pull mysql
docker run -d -p 13306:3306 --name lemon-mysql-container -e MYSQL_ROOT_PASSWORD=lemon mysql --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
docker exec -it lemon-mysql-container mysql -uroot -p
```

- Insert password: lemon

```
create user 'admin' identified by 'lemon';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%';
exit
```
- EN IDE // IMPORTAR DB DUMP ?

create database lemon_db;
USE users_db;
CREATE TABLE users
(
ID DECIMAL(10, 0) NOT NULL
, FIRST_NAME VARCHAR(25) NOT NULL
, LAST_NAME VARCHAR(25) NOT NULL
, EMAIL_ADDRESS VARCHAR(50) NOT NULL
, ALIAS VARCHAR(50) NOT NULL
, CONSTRAINT PRIMARY KEY ( ID ) );

```
docker network create --driver bridge lemon-net
docker network connect lemon-net lemon-mysql-container

mvn clean package
docker build -t lemon-api:V1 .

docker run --network lemon-net -d -p 18888:8888 --name lemon-api-container LemonApiImageId
```
