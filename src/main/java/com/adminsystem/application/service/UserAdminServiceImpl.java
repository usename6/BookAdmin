package com.adminsystem.application.service;

import com.adminsystem.application.common.utils.BeanConvertor;
import com.adminsystem.application.component.domain.BookAdminDO;
import com.adminsystem.application.component.dto.BookAdminDTO;
import com.adminsystem.application.storage.UserAdminStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdminServiceImpl implements UserAdminService{
    @Autowired
    private UserAdminStorage userAdminStorage;
    /**
     * 插入图书管理员信息
     * @param bookAdminDO
     * @return 插入的行数
     */
    @Override
    public Integer insert(BookAdminDO bookAdminDO){
        BookAdminDTO bookAdminDTO = BeanConvertor.to(bookAdminDO, BookAdminDTO.class);
        return userAdminStorage.insert(bookAdminDTO);
    }

    /**
     * 修改图书管理员信息
     * @param bookAdminDO
     * @return 插入的行数
     */
    @Override
    public Integer update(BookAdminDO bookAdminDO){
        BookAdminDTO bookAdminDTO = BeanConvertor.to(bookAdminDO, BookAdminDTO.class);
        return userAdminStorage.update(bookAdminDTO);
    }


    /**
     * 根据id查询图书管理员信息
     * @param id
     * @return 图书管理员信息
     */
    @Override
    public BookAdminDO getById(Integer id){
        return BeanConvertor.to(
                userAdminStorage.getById(id),
                BookAdminDO.class
        );
    }

    /**
     * 根据名称查询图书管理员信息
     * @param name
     * @return 返回满足条件的图书管理员信息
     */
    @Override
    public List<BookAdminDO> getByName(String name){
        return BeanConvertor.to(
                userAdminStorage.getByName(name),
                BookAdminDO.class
        );
    }

    /**
     * 根据用户名查询图书管理员信息
     * @param username
     * @return List<BookAdminDTO> 满足条件的员工列表
     */
    @Override
    public BookAdminDO getByUserName(String username){
        return BeanConvertor.to(userAdminStorage.getByUserName(username), BookAdminDO.class);
    }

    /**
     * 根据id删除对应图书管理员信息
     * @param id
     * @return 返回影响的行数
     */
    @Override
    public Integer delete(Integer id){
        return userAdminStorage.delete(id);
    }
}