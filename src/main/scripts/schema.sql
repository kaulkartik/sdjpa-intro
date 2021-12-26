drop table IF exists book;
drop table IF exists hibernate_sequence;

create table book (
   id bigint not null,
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    primary key (id)
)   engine=InnoDB


create table hibernate_sequence (
   next_val bigint
) engine=InnoDB


insert into hibernate_sequence values ( 1 )