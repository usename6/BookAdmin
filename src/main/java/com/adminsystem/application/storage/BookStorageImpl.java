package com.adminsystem.application.storage;

import com.adminsystem.application.cache.BookCacheStorage;
import com.adminsystem.application.common.utils.BeanConvertor;
import com.adminsystem.application.component.dto.BookInfoDTO;
import com.adminsystem.application.component.po.BookInfoPO;
import com.adminsystem.application.repository.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class BookStorageImpl implements BookStorage{
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookCacheStorage bookCacheStorage;
    /**
     * 添加新的数据
     * @param bookInfoDTO
     * @return 影响的行数
     */
    public Integer insert(BookInfoDTO bookInfoDTO){
        return bookMapper.insert(BeanConvertor.to(bookInfoDTO, BookInfoPO.class));
    }

    /**
     * 根据Id获得书籍信息
     * @param id
     * @return 返回符合条件的书籍信息
     */
    public BookInfoDTO getById(Integer id){
        BookInfoPO bookInfoPO = bookCacheStorage.get(id);
        if(ObjectUtils.isEmpty(bookInfoPO)){
            bookInfoPO = bookMapper.selectById(id);
            if(!ObjectUtils.isEmpty(bookInfoPO))
                bookCacheStorage.set(id, bookInfoPO);
        }
        return bookInfoPO == null ? null : BeanConvertor.to(bookInfoPO, BookInfoDTO.class);
    }

    /**
     * 根据名称获得书籍信息
     * @param name
     * @return 返回符合条件的书籍信息
     */
    public List<BookInfoDTO> getByName(String name){
        return BeanConvertor.to(bookMapper.getByName(name), BookInfoDTO.class);
    }

    /**
     * 根据作者名称获得书籍信息
     * @param author
     * @return 返回符合条件的书籍信息
     */
    public List<BookInfoDTO> getByAuthor(String author){
        return BeanConvertor.to(bookMapper.getByAuthor(author), BookInfoDTO.class);
    }

    /**
     * 根据出版社名称获得书籍信息
     * @param publisher
     * @return 返回符合条件的书籍信息
     */
    public List<BookInfoDTO> getByPublisher(String publisher){
        return BeanConvertor.to(bookMapper.getByPublisher(publisher), BookInfoDTO.class);
    }

    /**
     * 删除书籍
     * @param id
     * @return 删除的行数
     */
    public Integer delete(Integer id){
        return bookMapper.deleteById(id);
    }
}