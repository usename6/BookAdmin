package com.adminsystem.application.repository;

import com.adminsystem.application.component.domain.BookAdminDO;
import com.adminsystem.application.component.po.BookAdminPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAdminMapper extends BaseMapper<BookAdminPO> {
    public List<BookAdminPO> getByName(@Param("name") String name);
    public BookAdminPO getByUserName(@Param("username") String username);

}
