package com.adminsystem.application.component.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;

@AllArgsConstructor
@Builder
@Data
@TableName("tb_adminuser")
public class BookAdminPO {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String jobNo;
    private Integer role;
    private Date createTime;
    private Date modifyTime;
    private Integer LastModifyAuthor;
    public BookAdminPO(){}
}