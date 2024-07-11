package com.adminsystem.application.component.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
public class BookInfoDTO implements Serializable {
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
    public BookInfoDTO(){}
}