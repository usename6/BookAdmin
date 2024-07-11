package com.adminsystem.application.controller;

import com.adminsystem.application.common.utils.BeanConvertor;
import com.adminsystem.application.component.domain.BookInfoDO;
import com.adminsystem.application.component.dto.BookInfoDTO;
import com.adminsystem.application.component.vo.BookInfoVO;
import com.adminsystem.application.service.BookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 根据Id查询书籍信息
     * @param id
     * @return 返回符合条件的书籍的信息
     */
    @ApiOperation(value = "根据id查询图书信息")
    @GetMapping("/get")
    @ApiImplicitParam(name = "id", value = "图书id", dataType = "Integer", paramType = "path", required = true)
    public BookInfoVO get(@RequestParam(value = "id", required = false) Integer id){
        return BeanConvertor.to(bookService.getById(id), BookInfoVO.class);
    }

    /**
     * 根据名称查询书籍的信息
     * @param name
     * @return 返回符合条件的书籍信息
     */
    @ApiOperation(value = "根据id查询图书信息")
    @GetMapping("/getByName")
    @ApiImplicitParam(name = "name", value = "图书名称", dataType = "String", paramType = "path", required = true)
    public List<BookInfoVO> getByName(@RequestParam(value = "name", required = false) String name){
        return BeanConvertor.to(bookService.getByName(name), BookInfoVO.class);
    }

    /**
     * 根据作者查询书籍的信息
     * @param author
     * @return 返回符合条件的书籍信息
     */
    @ApiOperation(value = "根据作者名称查询图书信息")
    @GetMapping("/getByAuthor")
    @ApiImplicitParam(name = "author", value = "作者名称", dataType = "String", paramType = "path", required = true)
    public List<BookInfoVO> getByAuthor(@RequestParam(value = "author", required = false) String author){
        return BeanConvertor.to(bookService.getByAuthor(author), BookInfoVO.class);
    }

    /**
     * 根据作者查询书籍的信息
     * @param publisher
     * @return 返回符合条件的书籍信息
     */
    @ApiOperation(value = "根据出版社名称查询图书信息")
    @GetMapping("/getByPublisher")
    @ApiImplicitParam(name = "publisher", value = "作者名称", dataType = "String", paramType = "path", required = true)
    public List<BookInfoVO> getByPublisher(@RequestParam(value = "publisher", required = false) String publisher){
        return BeanConvertor.to(bookService.getByPublisher(publisher), BookInfoVO.class);
    }

    /**
     * 添加书籍的信息
     * @param bookInfoVO
     * @return 是否添加成功的信息
     */
    @ApiOperation(value = "添加图书信息")
    @PostMapping("/add")
    @ApiImplicitParam(name = "bookInfoVO", value = "图书信息", dataType = "BookInfoVO", paramType = "path", required = true)
    public String add(@RequestBody BookInfoVO bookInfoVO){
        Integer result = bookService.insert(BeanConvertor.to(bookInfoVO, BookInfoDO.class));
        return result > 0 ? "添加成功" : "添加失败";
    }

    /**
     * 删除书籍的信息
     * @param id
     * @return 是否删除成功的信息
     */
    @ApiOperation(value = "添加图书信息")
    @GetMapping("/delete")
    @ApiImplicitParam(name = "id", value = "图书信息", dataType = "Integer", paramType = "path", required = true)
    public String delete(@RequestParam(value = "id", required = true) Integer id){
        Integer result = bookService.delete(id);
        return result > 0 ? "删除成功" : "删除失败";
    }

}