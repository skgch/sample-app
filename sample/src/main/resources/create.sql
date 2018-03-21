DROP TABLE if EXISTS user;
create table if not exists user (
   id          int          not null auto_increment,
   name        varchar(50)  not null,
   password    varchar(255) not null,
   email       varchar(255) not null,
   role        varchar(255) not null,
   primary key (id)
);