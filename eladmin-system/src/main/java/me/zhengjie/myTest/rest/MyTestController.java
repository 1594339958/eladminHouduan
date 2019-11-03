package me.zhengjie.myTest.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.myTest.domain.MyTest;
import me.zhengjie.myTest.service.MyTestService;
import me.zhengjie.myTest.service.dto.MyTestQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
@Api(tags = "MyTest管理")
@RestController
@RequestMapping("/api/myTest")
public class MyTestController {

    private final MyTestService myTestService;

    public MyTestController(MyTestService myTestService) {
        this.myTestService = myTestService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('myTest:list')")
    public void download(HttpServletResponse response, MyTestQueryCriteria criteria) throws IOException {
        myTestService.download(myTestService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询MyTest")
    @ApiOperation("查询MyTest")
    @PreAuthorize("@el.check('myTest:list')")
    public ResponseEntity getMyTests(MyTestQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(myTestService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增MyTest")
    @ApiOperation("新增MyTest")
    @PreAuthorize("@el.check('myTest:add')")
    public ResponseEntity create(@Validated @RequestBody MyTest resources){
        return new ResponseEntity<>(myTestService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改MyTest")
    @ApiOperation("修改MyTest")
    @PreAuthorize("@el.check('myTest:edit')")
    public ResponseEntity update(@Validated @RequestBody MyTest resources){
        myTestService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @Log("删除MyTest")
    @ApiOperation("删除MyTest")
    @PreAuthorize("@el.check('myTest:del')")
    public ResponseEntity delete(@PathVariable Integer id){
        myTestService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}