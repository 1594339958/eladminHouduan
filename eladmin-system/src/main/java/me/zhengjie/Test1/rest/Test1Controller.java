package me.zhengjie.Test1.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.Test1.domain.Test1;
import me.zhengjie.Test1.service.Test1Service;
import me.zhengjie.Test1.service.dto.Test1QueryCriteria;
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
@Api(tags = "Test1管理")
@RestController
@RequestMapping("/api/test1")
public class Test1Controller {

    private final Test1Service test1Service;

    public Test1Controller(Test1Service test1Service) {
        this.test1Service = test1Service;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('test1:list')")
    public void download(HttpServletResponse response, Test1QueryCriteria criteria) throws IOException {
        test1Service.download(test1Service.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询Test1")
    @ApiOperation("查询Test1")
    @PreAuthorize("@el.check('test1:list')")
    public ResponseEntity getTest1s(Test1QueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(test1Service.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增Test1")
    @ApiOperation("新增Test1")
    @PreAuthorize("@el.check('test1:add')")
    public ResponseEntity create(@Validated @RequestBody Test1 resources){
        return new ResponseEntity<>(test1Service.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改Test1")
    @ApiOperation("修改Test1")
    @PreAuthorize("@el.check('test1:edit')")
    public ResponseEntity update(@Validated @RequestBody Test1 resources){
        test1Service.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @Log("删除Test1")
    @ApiOperation("删除Test1")
    @PreAuthorize("@el.check('test1:del')")
    public ResponseEntity delete(@PathVariable Integer id){
        test1Service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}