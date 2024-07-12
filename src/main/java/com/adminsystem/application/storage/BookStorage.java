package com.adminsystem.application.storage;

import com.adminsystem.application.component.dto.BookInfoDTO;
import com.adminsystem.application.component.po.BookInfoPO;

import java.util.List;

public interface BookStorage {
    /**
     * 添加新的数据
     * @param bookInfoDTO
     * @return 影响的行数
     */
    Integer insert(BookInfoDTO bookInfoDTO);

    /**
     * 修改的数据
     * @param bookInfoDTO
     * @return 影响的行数
     */
    Integer update(BookInfoDTO bookInfoDTO);

    /**
     * 根据Id获得书籍信息
     * @param id
     * @return 返回符合条件的书籍信息
     */
    BookInfoDTO getById(Integer id);

    /**
     * 根据名称获得书籍信息
     * @param name
     * @return 返回符合条件的书籍信息
     */
    List<BookInfoDTO> getByName(String name);

    /**
     * 根据作者名称获得书籍信息
     * @param author
     * @return 返回符合条件的书籍信息
     */
    List<BookInfoDTO> getByAuthor(String author);

    /**
     * 根据出版社名称获得书籍信息
     * @param publisher
     * @return 返回符合条件的书籍信息
     */
    List<BookInfoDTO> getByPublisher(String publisher);

    /**
     * 删除书籍
     * @param id
     * @return 删除的行数
     */
    Integer delete(Integer id);
}
