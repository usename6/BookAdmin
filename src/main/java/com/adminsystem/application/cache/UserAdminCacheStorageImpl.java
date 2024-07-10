package com.adminsystem.application.cache;

import com.adminsystem.application.component.po.BookAdminPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserAdminCacheStorageImpl implements UserAdminCacheStorage{
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String keyprefix = "UserAdmin#";
    private String generateKey(String content){
        return keyprefix + content;
    }
    public BookAdminPO get(Integer id){
        return id == null ? null : (BookAdminPO)redisTemplate.opsForValue().get(generateKey(id.toString()));
    }
    public boolean delete(Integer id){
        return id == null ? false : redisTemplate.delete(generateKey(id.toString()));
    }
    public boolean set(Integer id, BookAdminPO bookAdminPO) {
        try {
            if(id == null) return false;
            redisTemplate.opsForValue().set(generateKey(id.toString()), bookAdminPO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}