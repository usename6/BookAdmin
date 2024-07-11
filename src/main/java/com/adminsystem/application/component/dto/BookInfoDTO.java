package com.adminsystem.application.component.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
public class BookInfoDTO {
    Integer id;
    String bookuuid;
    String name;
    String shelf_no;
    String book_type;
    Integer status;
    Date create_time;
    Date modify_time;
    Integer last_modify_author;
    String publisher;
    String author;
    public BookInfoDTO(){}
}