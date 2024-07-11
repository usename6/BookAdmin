package com.adminsystem.application.storage;

import com.adminsystem.application.cache.UserAdminCacheStorage;
import com.adminsystem.application.common.utils.BeanConvertor;
import com.adminsystem.application.component.dto.BookAdminDTO;
import com.adminsystem.application.component.po.BookAdminPO;
import com.adminsystem.application.repository.UserAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class UserAdminStorageImpl implements UserAdminStorage{
    @Autowired
    private UserAdminMapper userAdminMapper;
    @Autowired
    private UserAdminCacheStorage userAdminCacheStorage;
    /**
     * 插入图书管理员信息
     * @param bookAdminDTO 图书管理员的信息
     * @return 插入的行数
     */
    public Integer insert(BookAdminDTO bookAdminDTO){
        BookAdminPO bookAdminPO = BeanConvertor.to(bookAdminDTO, BookAdminPO.class);
        return userAdminMapper.insert(bookAdminPO);
    }

    /**
     * 根据id查询图书管理员信息
     * @param id
     * @return 图书管理员信息
     */
    public BookAdminDTO getById(Integer id){
        BookAdminPO bookAdminPO = userAdminCacheStorage.get(id);
        if(ObjectUtils.isEmpty(bookAdminPO)) {
            bookAdminPO = userAdminMapper.selectById(id);
            if(!ObjectUtils.isEmpty(bookAdminPO))
                userAdminCacheStorage.set(id, bookAdminPO);
        }
        return bookAdminPO == null ? null : BeanConvertor.to(bookAdminPO, BookAdminDTO.class);
    }

    /**
     * 根据名称查询图书管理员信息
     * @param name
     * @return 返回满足条件的图书管理员信息
     */
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
    public BookAdminDTO getByUserName(String username){
        return BeanConvertor.to(userAdminMapper.getByUserName(username), BookAdminDTO.class);
    }

    /**
     * 根据id删除对应图书管理员信息
     * @param id
     * @return 返回影响的行数
     */
    public Integer delete(Integer id){
        return userAdminMapper.deleteById(id);
    }
}