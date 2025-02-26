DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	version INT NULL COMMENT '版本',
	PRIMARY KEY (id)
);

create table sys_person
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	version TIMESTAMP NULL COMMENT '版本',
	PRIMARY KEY (id)
)