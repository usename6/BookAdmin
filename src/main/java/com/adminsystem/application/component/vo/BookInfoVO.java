package com.adminsystem.application.component.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "BookAdminVO",description = "图书信息")
@AllArgsConstructor
@Builder
@Data
public class BookInfoVO {
    @ApiModelProperty(value = "图书id", example = "0、1、2")
    Integer id;
    @ApiModelProperty(value = "bookuuid图书编号", example = "0000")
    String bookuuid;
    @ApiModelProperty(value = "图书名称", example = "基督山伯爵")
    String name;
    @ApiModelProperty(value = "书架的编号", example = "0、1、2")
    String shelf_no;
    @ApiModelProperty(value = "图书类型", example = "悬疑、科幻")
    String book_type;
    @ApiModelProperty(value = "是否上架", example = "0、1、2")
    Integer status;
    @ApiModelProperty(value = "创建时间", example = "2024.07.11 10.19:00")
    Date create_time;
    @ApiModelProperty(value = "修改时间", example = "2024.07.11 10.19:00")
    Date modify_time;
    @ApiModelProperty(value = "最后操作人id", example = "0、1、2")
    Integer last_modify_author;
    @ApiModelProperty(value = "出版社", example = "新华出版社")
    String publisher;
    @ApiModelProperty(value = "作者", example = "罗贯中")
    String author;
    public BookInfoVO(){}
}