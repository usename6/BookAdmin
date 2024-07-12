package com.adminsystem.application.service;

import com.adminsystem.application.component.domain.BookInfoDO;
import com.adminsystem.application.component.dto.BookInfoDTO;

import java.util.List;

public interface BookService {
    /**
     * 添加新的数据
     * @param bookInfoDO
     * @return 影响的行数
     */
    Integer insert(BookInfoDO bookInfoDO);

    /**
     * 修改的数据
     * @param bookInfoDO
     * @return 影响的行数
     */
    Integer update(BookInfoDO bookInfoDO);

    /**
     * 根据Id获得书籍信息
     * @param id
     * @return 返回符合条件的书籍信息
     */
    BookInfoDO getById(Integer id);

    /**
     * 根据名称获得书籍信息
     * @param name
     * @return 返回符合条件的书籍信息
     */
    List<BookInfoDO> getByName(String name);

    /**
     * 根据作者名称获得书籍信息
     * @param author
     * @return 返回符合条件的书籍信息
     */
    List<BookInfoDO> getByAuthor(String author);

    /**
     * 根据作者名称获得书籍信息
     * @param publisher
     * @return 返回符合条件的书籍信息
     */
    List<BookInfoDO> getByPublisher(String publisher);

    /**
     * 删除书籍
     * @param id
     * @return 删除的行数
     */
    Integer delete(Integer id);
}
