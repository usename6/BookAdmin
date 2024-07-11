package com.adminsystem.application.controller;

import com.adminsystem.application.common.utils.BeanConvertor;
import com.adminsystem.application.component.domain.BookAdminDO;
import com.adminsystem.application.component.po.BookAdminPO;
import com.adminsystem.application.component.vo.BookAdminVO;
import com.adminsystem.application.service.UserAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Api(value = "图书管理员的控制器API", tags = "控制器API")
@RestController
@RequestMapping("/userAdmin")
public class UserAdminController {
    @Autowired
    private UserAdminService userAdminService;

    private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

    /**
     * 查询图书管理员的信息
     * @param id
     * @return 返回符合条件的图书管理员的信息
     */
    @ApiOperation(value = "根据id查询图书管理员信息")
    @GetMapping("/get")
    @ApiImplicitParam(name = "id", value = "图书管理id", dataType = "Integer", paramType = "path", required = true)
    public BookAdminPO get(@RequestParam(value = "id", required = true) Integer id){
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
    @ApiOperation(value = "添加图书管理员信息")
    @PostMapping("/add")
    @ApiImplicitParam(name = "bookAdminPO", value = "图书管理员信息", dataType = "BookAdminPO", paramType = "path", required = true)
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
    @ApiOperation(value = "根据真实名称查询图书管理员信息")
    @GetMapping("/getByName")
    @ApiImplicitParam(name = "name", value = "图书管理员名称", dataType = "String", paramType = "path", required = true)
    public List<BookAdminVO> getByName(@RequestParam(value = "name", required = false) String name){
        return BeanConvertor.to(userAdminService.getByName(name), BookAdminVO.class);
    }

    /**
     * 从账号名称活动图书管理员信息
     * @param username
     * @return 符合条件的图书管理员信息
     */
    @ApiOperation(value = "根据用户名查询图书管理员信息")
    @GetMapping("/getByUserName")
    @ApiImplicitParam(name = "username", value = "图书管理员用户名", dataType = "String", paramType = "path", required = true)
    public BookAdminVO getByUserName(@RequestParam(value = "username", required = false) String username){
        return BeanConvertor.to(userAdminService.getByUserName(username), BookAdminVO.class);
    }
}