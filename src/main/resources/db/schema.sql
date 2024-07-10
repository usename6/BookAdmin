DROP TABLE IF EXISTS tb_adminuser;
CREATE TABLE tb_adminuser
(
    id BIGINT NOT NULL COMMENT 'id',
    username VARCHAR(20) NOT NULL COMMENT '账户名',
    password VARCHAR(20) NOT NULL COMMENT '密码',
    name VARCHAR(20) NOT NULL COMMENT '员工真实姓名',
    job_no VARCHAR(20) NOT NULL COMMENT '工号',
    role INT(1) NOT NULL COMMENT '0表示超级管理员，1表示普通管理员',
    create_time DATE NOT NULL COMMENT '创建时间',
    modify_time DATE NOT NULL COMMENT '修改时间',
    last_modify_author BIGINT NOT NULL COMMENT '最后操作人',
    PRIMARY KEY (id)
);

