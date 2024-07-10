package com.adminsystem.application.component.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
public class BookAdminVO {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String jobNo;
    private Integer role;
    private Date createTime;
    private Date modifyTime;
    private Integer LastModifyAuthor;
    public BookAdminVO(){}
}