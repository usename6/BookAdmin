package com.adminsystem.application.controller;

import com.adminsystem.application.common.utils.BeanConvertor;
import com.adminsystem.application.component.domain.BookAdminDO;
import com.adminsystem.application.component.po.BookAdminPO;
import com.adminsystem.application.component.vo.BookAdminVO;
import com.adminsystem.application.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/userAdmin")
public class UserAdminController {
    @Autowired
    private UserAdminService userAdminService;

    /**
     * 查询图书管理员的信息
     * @param id
     * @return 返回符合条件的图书管理员的信息
     */
    @GetMapping("/get")
    public BookAdminPO get(@RequestParam(value = "id", required = false) Integer id){
        if(ObjectUtils.isEmpty(id)){
            return null;
        }
        return BeanConvertor.to(userAdminService.getById(id), BookAdminPO.class);
    }

    /**
     * 添加图书馆管理员信息
     * @param bookAdminPO
     * @return 是否添加成功的提示
     */
    @PostMapping("/add")
    public String add(@RequestBody BookAdminPO bookAdminPO){
        bookAdminPO.setCreateTime(new Date());
        bookAdminPO.setModifyTime(new Date());
        Integer count = userAdminService.insert(BeanConvertor.to(bookAdminPO, BookAdminDO.class));
        String tips = count > 0 ? "success" : "fail";
        return tips;
    }

    /**
     * 从真实姓名中匹配管理员信息
     * @param name
     * @return 符合条件的图书馆管理员的信息列表
     */
    @GetMapping("/getByName")
    public List<BookAdminVO> getByName(@RequestParam(value = "name", required = false) String name){
        return BeanConvertor.to(userAdminService.getByName(name), BookAdminVO.class);
    }

    /**
     * 从账号名称活动图书管理员信息
     * @param username
     * @return 符合条件的图书管理员信息
     */
    @GetMapping("/getByUserName")
    public BookAdminVO getByUserName(@RequestParam(value = "username", required = false) String username){
        return BeanConvertor.to(userAdminService.getByUserName(username), BookAdminVO.class);
    }
}