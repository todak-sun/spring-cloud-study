create table users
(
	id bigint auto_increment,
	email varchar(20) not null,
	name varchar(50) not null,
	uuid varchar(40) not null,
	password varchar(100) not null,
	constraint users_pk
		primary key (id)
)
comment '사용자';

create unique index users_email_uindex
	on users (email);

create unique index users_name_uindex
	on users (name);

create unique index users_password_uindex
	on users (password);

create unique index users_uuid_uindex
	on users (uuid);
