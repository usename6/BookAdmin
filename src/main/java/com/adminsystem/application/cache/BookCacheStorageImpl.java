package com.adminsystem.application.cache;

import com.adminsystem.application.component.po.BookInfoPO;
import io.swagger.models.auth.In;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class BookCacheStorageImpl implements BookCacheStorage{
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    private static final String keyprefix = "Book_";
    private String generateKey(String content){
        return keyprefix + content;
    }
    /**
     * 尝试获取redis的分布式锁
     * @param id
     * @param waitTime
     * @return 是否成功获得锁
     */
    @Override
    public boolean tryLock(Integer id, int waitTime) {
        String key = generateKey(id.toString());
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(waitTime, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 尝试解除redis的分布式锁
     * @param id
     * @return 是否成功获得锁
     */
    @Override
    public void unLock(Integer id) {
        String key = generateKey(id.toString());
        RLock lock = redissonClient.getLock(key);
        if (lock.isLocked() && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /**
     * 判断是否是有这个key
     * @param id
     * @return
     */
    public boolean haskey(Integer id){
        return redisTemplate.hasKey(generateKey(id.toString()));
    }

    /**
     * 从缓存中获取数据
     * @param id
     * @return 返回缓存中的数据
     */
    @Override
    public BookInfoPO get(Integer id){
        return id == null ? null : (BookInfoPO) redisTemplate.opsForValue().get(generateKey(id.toString()));
    }

    /**
     * 删除缓存
     * @param id
     * @return 是否删除缓存成功
     */
    @Override
    public boolean delete(Integer id){
        return id == null ? false : redisTemplate.delete(generateKey(id.toString()));
    }

    /**
     * 设置缓存
     * @param id
     * @param bookInfoPO
     * @return 缓存是否设置成功
     */
    @Override
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