package com.adminsystem.application.service;

import com.adminsystem.application.common.utils.BeanConvertor;
import com.adminsystem.application.component.domain.BookInfoDO;
import com.adminsystem.application.component.dto.BookInfoDTO;
import com.adminsystem.application.storage.BookStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookStorage bookStorage;
    /**
     * 添加新的数据
     * @param bookInfoDO
     * @return 影响的行数
     */
    @Override
    public Integer insert(BookInfoDO bookInfoDO){
        return bookStorage.insert(BeanConvertor.to(bookInfoDO, BookInfoDTO.class));
    }

    /**
     * 根据Id获得书籍信息
     * @param id
     * @return 返回符合条件的书籍信息
     */
    @Override
    public BookInfoDO getById(Integer id){
        return BeanConvertor.to(bookStorage.getById(id), BookInfoDO.class);
    }

    /**
     * 根据名称获得书籍信息
     * @param name
     * @return 返回符合条件的书籍信息
     */
    @Override
    public List<BookInfoDO> getByName(String name){
        return BeanConvertor.to(bookStorage.getByName(name), BookInfoDO.class);
    }

    /**
     * 根据作者名称获得书籍信息
     * @param author
     * @return 返回符合条件的书籍信息
     */
    @Override
    public List<BookInfoDO> getByAuthor(String author){
        return BeanConvertor.to(bookStorage.getByAuthor(author), BookInfoDO.class);
    }

    /**
     * 根据出版社名称获得书籍信息
     * @param publisher
     * @return 返回符合条件的书籍信息
     */
    @Override
    public List<BookInfoDO> getByPublisher(String publisher){
        return BeanConvertor.to(bookStorage.getByPublisher(publisher), BookInfoDO.class);
    }

    /**
     * 删除书籍
     * @param id
     * @return 删除的行数
     */
    @Override
    public Integer delete(Integer id){
        return bookStorage.delete(id);
    }
}