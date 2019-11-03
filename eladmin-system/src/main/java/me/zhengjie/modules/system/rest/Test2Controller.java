package me.zhengjie.modules.system.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.system.domain.Test2;
import me.zhengjie.modules.system.service.Test2Service;
import me.zhengjie.modules.system.service.dto.Test2QueryCriteria;
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
@Api(tags = "Test2管理")
@RestController
@RequestMapping("/api/test2")
public class Test2Controller {

    private final Test2Service test2Service;

    public Test2Controller(Test2Service test2Service) {
        this.test2Service = test2Service;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('test2:list')")
    public void download(HttpServletResponse response, Test2QueryCriteria criteria) throws IOException {
        test2Service.download(test2Service.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询Test2")
    @ApiOperation("查询Test2")
    @PreAuthorize("@el.check('test2:list')")
    public ResponseEntity getTest2s(Test2QueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(test2Service.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增Test2")
    @ApiOperation("新增Test2")
    @PreAuthorize("@el.check('test2:add')")
    public ResponseEntity create(@Validated @RequestBody Test2 resources){
        return new ResponseEntity<>(test2Service.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改Test2")
    @ApiOperation("修改Test2")
    @PreAuthorize("@el.check('test2:edit')")
    public ResponseEntity update(@Validated @RequestBody Test2 resources){
        test2Service.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @Log("删除Test2")
    @ApiOperation("删除Test2")
    @PreAuthorize("@el.check('test2:del')")
    public ResponseEntity delete(@PathVariable Integer id){
        test2Service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}