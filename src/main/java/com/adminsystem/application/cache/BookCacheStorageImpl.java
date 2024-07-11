package com.adminsystem.application.cache;

import com.adminsystem.application.component.po.BookInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookCacheStorageImpl implements BookCacheStorage{
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String keyprefix = "Book#";
    private String generateKey(String content){
        return keyprefix + content;
    }
    public BookInfoPO get(Integer id){
        return id == null ? null :(BookInfoPO) redisTemplate.opsForValue().get(generateKey(id.toString()));
    }
    public boolean delete(Integer id){
        return id == null ? false : redisTemplate.delete(generateKey(id.toString()));
    }
    public boolean set(Integer id, BookInfoPO bookInfoPO){
        try {
            if(id == null) return false;
            redisTemplate.opsForValue().set(generateKey(id.toString()), bookInfoPO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}