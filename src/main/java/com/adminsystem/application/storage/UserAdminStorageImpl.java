package com.adminsystem.application.storage;

import com.adminsystem.application.cache.UserAdminCacheStorage;
import com.adminsystem.application.common.utils.BeanConvertor;
import com.adminsystem.application.component.dto.BookAdminDTO;
import com.adminsystem.application.component.po.BookAdminPO;
import com.adminsystem.application.repository.UserAdminMapper;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class UserAdminStorageImpl implements UserAdminStorage{
    private static final Logger logger = LoggerFactory.getLogger(UserAdminStorage.class);

    @Autowired
    private UserAdminMapper userAdminMapper;
    @Autowired
    private UserAdminCacheStorage userAdminCacheStorage;

    private final Integer STATUS_GETLOCKER = 2;
    private final Integer STATUS_HASKEY = 1;
    private final Integer STATUS_FAILED = 0;

    private BookAdminPO updateCache(Integer id){
        BookAdminPO bookAdminPO = userAdminMapper.selectById(id);
        if(!ObjectUtils.isEmpty(bookAdminPO))
            userAdminCacheStorage.set(id, bookAdminPO);
        return bookAdminPO;
    }

    /**
     * 插入图书管理员信息
     * @param bookAdminDTO 图书管理员的信息
     * @return 插入的行数
     */
    @Override
    public Integer insert(BookAdminDTO bookAdminDTO){
        BookAdminPO bookAdminPO = BeanConvertor.to(bookAdminDTO, BookAdminPO.class);
        Integer result =  userAdminMapper.insert(bookAdminPO);
        if(result < 0){
            logger.info("[插入图书管理员信息失败]: {}", bookAdminPO.toString());
        }
        return result;
    }

    /**
     * 根据id查询图书管理员信息
     * @param id
     * @return 图书管理员信息
     */
    @Cacheable(cacheNames = "users", key = "#id")
    @Override
    public BookAdminDTO getById(Integer id){
        if(ObjectUtils.isEmpty(id)){
            logger.info("[查询图书管理员信息失败]: id为空");
            return null;
        }
        BookAdminPO bookAdminPO = userAdminCacheStorage.get(id);
        if(ObjectUtils.isEmpty(bookAdminPO)) {
            int count = 0;
            int flag = STATUS_FAILED;
            while(true){
                if(userAdminCacheStorage.haskey(id)){
                    flag = STATUS_HASKEY;
                    break;
                }
                if(userAdminCacheStorage.tryLock(id, 1000)){
                    flag = STATUS_GETLOCKER;
                    break;
                }
                count++;
                if(count >= 10){
                    break;
                }
            }
            if(flag == STATUS_GETLOCKER){
                updateCache(id);
                userAdminCacheStorage.unLock(id);
            }
            else if(flag == STATUS_HASKEY){
                bookAdminPO = userAdminCacheStorage.get(id);
                return BeanConvertor.to(bookAdminPO, BookAdminDTO.class);
            }
            else{
                //此时压力太大了请求太多了，放弃这个请求
                logger.info("[查询图书管理员信息失败]: id = {}, 请求量过大", id);
                return null;
            }
        }
        if(bookAdminPO == null)
            logger.info("[查询图书管理员信息失败]: id : {} , bookAdminPO为空", id);
        return bookAdminPO == null ? null : BeanConvertor.to(bookAdminPO, BookAdminDTO.class);
    }

    /**
     * 根据名称查询图书管理员信息
     * @param name
     * @return 返回满足条件的图书管理员信息
     */
    @Override
    public List<BookAdminDTO> getByName(String name){
        List<BookAdminDTO> bookAdminDTO = BeanConvertor.to(
                userAdminMapper.getByName(name), BookAdminDTO.class);
        return bookAdminDTO;
    }

    /**
     * 根据用户名查询图书管理员信息
     * @param username
     * @return List<BookAdminDTO> 满足条件的员工列表
     */
    @Override
    public BookAdminDTO getByUserName(String username){
        return BeanConvertor.to(userAdminMapper.getByUserName(username), BookAdminDTO.class);
    }

    /**
     * 根据id删除对应图书管理员信息
     * @param id
     * @return 返回影响的行数
     */
    @Override
    public Integer delete(Integer id){
        Integer result = userAdminMapper.deleteById(id);
        if(result < 0){
            logger.info("[删除图书管理员信息失败]: id = {}", id);
        }
        return result;
    }
}