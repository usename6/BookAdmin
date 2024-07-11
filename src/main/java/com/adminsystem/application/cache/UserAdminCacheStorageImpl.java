package com.adminsystem.application.cache;

import com.adminsystem.application.component.po.BookAdminPO;
import com.adminsystem.application.storage.UserAdminStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class UserAdminCacheStorageImpl implements UserAdminCacheStorage{
    private static final Logger logger = LoggerFactory.getLogger(UserAdminStorage.class);
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String keyprefix = "UserAdmin#";
    private String generateKey(String content){
        return keyprefix + content;
    }
    public BookAdminPO get(Integer id){
        if(ObjectUtils.isEmpty(id)){
            logger.info("[查询图书管理员信息缓存失败]: id为空");
        }
        return id == null ? null : (BookAdminPO)redisTemplate.opsForValue().get(generateKey(id.toString()));
    }
    public boolean delete(Integer id){
        if(ObjectUtils.isEmpty(id)){
            logger.info("[删除图书管理员信息缓存失败]: id为空");
        }
        return id == null ? false : redisTemplate.delete(generateKey(id.toString()));
    }
    public boolean set(Integer id, BookAdminPO bookAdminPO) {
        try {
            if(id == null) return false;
            redisTemplate.opsForValue().set(generateKey(id.toString()), bookAdminPO);
            return true;
        } catch (Exception e) {
            logger.info("[设置图书管理员信息缓存失败]: id = {}", id);
            e.printStackTrace();
            return false;
        }
    }
}