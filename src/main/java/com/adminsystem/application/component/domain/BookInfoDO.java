package com.adminsystem.application.component.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@Builder
@Data
public class BookInfoDO implements Serializable {
    Integer id;
    String bookuuid;
    String name;
    String shelf_no;
    String shelfNo;
    String bookType;
    Integer status;
    Date createTime;
    Date modifyTime;
    Integer LastModifyAuthor;
    String publisher;
    String author;
    public BookInfoDO(){}
}