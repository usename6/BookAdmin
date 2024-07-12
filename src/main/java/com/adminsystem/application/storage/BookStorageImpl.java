package com.adminsystem.application.storage;

import com.adminsystem.application.cache.BookCacheStorage;
import com.adminsystem.application.common.utils.BeanConvertor;
import com.adminsystem.application.common.utils.ZooKeeperGeneratePrimaryKey;
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
    private static final Logger logger = LoggerFactory.getLogger(BookStorageImpl.class);

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookCacheStorage bookCacheStorage;
    @Autowired
    private ZooKeeperGeneratePrimaryKey zooKeeperGeneratePrimaryKey;

    private final Integer STATUS_GETLOCKER = 2;
    private final Integer STATUS_HASKEY = 1;
    private final Integer STATUS_FAILED = 0;
    private final String BOOKPRIMARYKEY = "BOOK";
    /**
     * 添加新的数据
     * @param bookInfoDTO
     * @return 影响的行数
     */
    @Override
    public Integer insert(BookInfoDTO bookInfoDTO){
        if(ObjectUtils.isEmpty(bookInfoDTO.getId())){
            //zookeeper做全局计数器
            bookInfoDTO.setId(zooKeeperGeneratePrimaryKey.generate(BOOKPRIMARYKEY));
        }
        Integer result = bookMapper.insert(BeanConvertor.to(bookInfoDTO, BookInfoPO.class));
        if(result < 0){
            logger.info("[书籍添加失败]: bookInfoDTO = {}", bookInfoDTO.toString());
        }
        return result;
    }

    /**
     * 修改的数据
     * @param bookInfoDTO
     * @return 影响的行数
     */
    @Override
    public Integer update(BookInfoDTO bookInfoDTO){
        Integer result = bookMapper.updateById(BeanConvertor.to(bookInfoDTO, BookInfoPO.class));
        if(result < 0){
            logger.info("[书籍添加失败]: bookInfoDTO = {}", bookInfoDTO.toString());
        }
        return result;
    }

    private BookInfoPO updateCache(Integer id){
        BookInfoPO bookInfoPO = bookMapper.selectById(id);
        if(!ObjectUtils.isEmpty(bookInfoPO))
            bookCacheStorage.set(id, bookInfoPO);
        return bookInfoPO;
    }

    /**
     * 根据Id获得书籍信息
     * @param id
     * @return 返回符合条件的书籍信息
     */
    @Cacheable(cacheNames = "books", key = "#id")
    @Override
    public BookInfoDTO getById(Integer id){
        logger.info("[查询书籍信息]: 未命中本地缓存!");
        if(ObjectUtils.isEmpty(id)){
            logger.info("[查询书籍信息失败]: id为空");
            return null;
        }
        BookInfoPO bookInfoPO = bookCacheStorage.get(id);
        if(ObjectUtils.isEmpty(bookInfoPO)){
            int status = STATUS_FAILED;
            int count = 0;
            while(true){
                //尝试获得分布式锁，如果可以break
                if(bookCacheStorage.tryLock(id, 1000)){
                    status = STATUS_GETLOCKER;
                    break;
                }
                //尝试判断缓存是否有值，有的话break
                if(bookCacheStorage.haskey(id)){
                    status = STATUS_HASKEY;
                    break;
                }
                count++;
                if(count >= 10) break;
            }
            //成功获得分布式锁
            if(status == STATUS_GETLOCKER){
                //更新缓存和数据库
                bookInfoPO = updateCache(id);
                //分布式解锁
                bookCacheStorage.unLock(id);
                return BeanConvertor.to(bookInfoPO, BookInfoDTO.class);
            }
            else if(status == STATUS_HASKEY){
                //判断是否有数据
                bookInfoPO = bookCacheStorage.get(id);
                return BeanConvertor.to(bookInfoPO, BookInfoDTO.class);
            }
            else{
                //此时压力太大了请求太多了，放弃这个请求
                logger.info("[查询书籍信息失败]: id = {}, 请求量过大", id);
                return null;
            }
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