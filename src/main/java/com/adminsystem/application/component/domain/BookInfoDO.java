package com.adminsystem.application.component.domain;

import java.util.Date;

public class BookInfoDO {
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
    public BookInfoDO(){}
}