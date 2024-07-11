package com.adminsystem.application.storage;

import com.adminsystem.application.cache.BookCacheStorage;
import com.adminsystem.application.common.utils.BeanConvertor;
import com.adminsystem.application.component.dto.BookInfoDTO;
import com.adminsystem.application.component.po.BookInfoPO;
import com.adminsystem.application.repository.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class BookStorageImpl implements BookStorage{
    private static final Logger logger = LoggerFactory.getLogger(UserAdminStorage.class);

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookCacheStorage bookCacheStorage;
    /**
     * 添加新的数据
     * @param bookInfoDTO
     * @return 影响的行数
     */
    @Override
    public Integer insert(BookInfoDTO bookInfoDTO){
        Integer result = bookMapper.insert(BeanConvertor.to(bookInfoDTO, BookInfoPO.class));
        if(result < 0){
            logger.info("[书籍添加失败]: bookInfoDTO = {}", bookInfoDTO.toString());
        }
        return result;
    }

    /**
     * 根据Id获得书籍信息
     * @param id
     * @return 返回符合条件的书籍信息
     */
    @Cacheable(cacheNames = "books", key = "#id")
    @Override
    public BookInfoDTO getById(Integer id){
        System.out.println("yes");
        if(ObjectUtils.isEmpty(id)){
            logger.info("[查询书籍信息失败]: id为空");
            return null;
        }
        BookInfoPO bookInfoPO = bookCacheStorage.get(id);
        if(ObjectUtils.isEmpty(bookInfoPO)){
            System.out.println("redis no");
            bookInfoPO = bookMapper.selectById(id);
            if(!ObjectUtils.isEmpty(bookInfoPO))
                bookCacheStorage.set(id, bookInfoPO);
        }
        if(ObjectUtils.isEmpty(bookInfoPO)){
            logger.info("[查询书籍信息失败]: id = {}", id);
        }
        return bookInfoPO == null ? null : BeanConvertor.to(bookInfoPO, BookInfoDTO.class);
    }

    /**
     * 根据名称获得书籍信息
     * @param name
     * @return 返回符合条件的书籍信息
     */
    @Override
    public List<BookInfoDTO> getByName(String name){
        return BeanConvertor.to(bookMapper.getByName(name), BookInfoDTO.class);
    }

    /**
     * 根据作者名称获得书籍信息
     * @param author
     * @return 返回符合条件的书籍信息
     */
    @Override
    public List<BookInfoDTO> getByAuthor(String author){
        return BeanConvertor.to(bookMapper.getByAuthor(author), BookInfoDTO.class);
    }

    /**
     * 根据出版社名称获得书籍信息
     * @param publisher
     * @return 返回符合条件的书籍信息
     */
    @Override
    public List<BookInfoDTO> getByPublisher(String publisher){
        return BeanConvertor.to(bookMapper.getByPublisher(publisher), BookInfoDTO.class);
    }

    /**
     * 删除书籍
     * @param id
     * @return 删除的行数
     */
    @Override
    public Integer delete(Integer id){
        return bookMapper.deleteById(id);
    }
}