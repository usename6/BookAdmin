package com.adminsystem.application.component.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@TableName("tb_book")
public class BookInfoPO {
    Integer id;
    String bookuuid;
    String name;
    String shelfNo;
    String bookType;
    Integer status;
    Date createTime;
    Date modifyTime;
    Integer LastModifyAuthor;
    String publisher;
    String author;
    public BookInfoPO(){}
}