package com.adminsystem.application.component.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "BookAdminVO",description = "图书管理员信息")
@AllArgsConstructor
@Builder
@Data
public class BookAdminVO {
    @ApiModelProperty(value = "图书管理员id", example = "0、1、2")
    private Integer id;
    @ApiModelProperty(value = "图书管理员真实姓名", example = "Jack、Tom")
    private String name;
    @ApiModelProperty(value = "图书管理员用户名", example = "root")
    private String username;
    @ApiModelProperty(value = "图书管理员密码", example = "123456")
    private String password;
    @ApiModelProperty(value = "图书管理员工号", example = "NXXXXX")
    private String jobNo;
    @ApiModelProperty(value = "图书管理员的角色控制", example = "0、1")
    private Integer role;
    @ApiModelProperty(value = "图书管理员的创建时间", example = "2024.07.11 10:13:00")
    private Date createTime;
    @ApiModelProperty(value = "图书管理员的修改时间", example = "2024.07.11 10:13:00")
    private Date modifyTime;
    @ApiModelProperty(value = "图书管理员的最后修改人id", example = "0、1、2、3")
    private Integer LastModifyAuthor;
    public BookAdminVO(){}
}