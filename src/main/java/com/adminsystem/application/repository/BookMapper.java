package com.adminsystem.application.repository;

import com.adminsystem.application.component.po.BookInfoPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<BookInfoPO> {
    List<BookInfoPO> getByName(@Param("name") String name);
    List<BookInfoPO> getByAuthor(@Param("author") String author);
    List<BookInfoPO> getByPublisher(@Param("publisher") String publisher);
}
