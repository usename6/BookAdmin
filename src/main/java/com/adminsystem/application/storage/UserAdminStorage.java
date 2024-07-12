package com.adminsystem.application.storage;

import com.adminsystem.application.component.dto.BookAdminDTO;

import java.util.List;

public interface UserAdminStorage {
    /**
     * 插入图书管理员信息
     * @param bookAdminDTO 图书管理员的信息
     * @return 插入的行数
     */
    Integer insert(BookAdminDTO bookAdminDTO);

    /**
     * 修改图书管理员信息
     * @param bookAdminDTO 图书管理员的信息
     * @return 插入的行数
     */
    Integer update(BookAdminDTO bookAdminDTO);

    /**
     * 根据id查询图书管理员信息
     * @param id
     * @return 图书管理员信息
     */
    BookAdminDTO getById(Integer id);

    /**
     * 根据名称查询图书管理员信息
     * @param name
     * @return 返回满足条件的图书管理员信息
     */
    List<BookAdminDTO> getByName(String name);

    /**
     * 根据用户名查询图书管理员信息
     * @param username
     * @return List<BookAdminDTO> 满足条件的员工列表
     */
    BookAdminDTO getByUserName(String username);

    /**
     * 根据id删除对应图书管理员信息
     * @param id
     * @return 返回影响的行数
     */
    Integer delete(Integer id);

}
