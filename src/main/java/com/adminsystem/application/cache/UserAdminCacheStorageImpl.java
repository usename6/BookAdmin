package com.adminsystem.application.cache;

import com.adminsystem.application.component.po.BookAdminPO;
import com.adminsystem.application.storage.UserAdminStorage;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

@Component
public class UserAdminCacheStorageImpl implements UserAdminCacheStorage{
    private static final Logger logger = LoggerFactory.getLogger(UserAdminStorage.class);
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String keyprefix = "UserAdmin_";
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