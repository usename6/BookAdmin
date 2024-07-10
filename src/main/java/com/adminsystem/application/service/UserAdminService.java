package com.adminsystem.application.service;

import com.adminsystem.application.component.domain.BookAdminDO;

import java.util.List;

public interface UserAdminService {
    /**
     * 插入图书管理员信息
     * @param bookAdminDO
     * @return 插入的行数
     */
    Integer insert(BookAdminDO bookAdminDO);


    /**
     * 根据id查询图书管理员信息
     * @param id
     * @return 图书管理员信息
     */
    BookAdminDO getById(Integer id);

    /**
     * 根据名称查询图书管理员信息
     * @param name
     * @return 返回满足条件的图书管理员信息
     */
    List<BookAdminDO> getByName(String name);

    /**
     * 根据用户名查询图书管理员信息
     * @param username
     * @return List<BookAdminDTO> 满足条件的员工列表
     */
    BookAdminDO getByUserName(String username);

    /**
     * 根据id删除对应图书管理员信息
     * @param id
     * @return 返回影响的行数
     */
    Integer delete(Integer id);

}
