package me.zhengjie.Test1.service.impl;

import me.zhengjie.Test1.domain.Test1;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.Test1.repository.Test1Repository;
import me.zhengjie.Test1.service.Test1Service;
import me.zhengjie.Test1.service.dto.Test1DTO;
import me.zhengjie.Test1.service.dto.Test1QueryCriteria;
import me.zhengjie.Test1.service.mapper.Test1Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
@Service
@CacheConfig(cacheNames = "test1")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class Test1ServiceImpl implements Test1Service {

    private final Test1Repository test1Repository;

    private final Test1Mapper test1Mapper;

    public Test1ServiceImpl(Test1Repository test1Repository, Test1Mapper test1Mapper) {
        this.test1Repository = test1Repository;
        this.test1Mapper = test1Mapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(Test1QueryCriteria criteria, Pageable pageable){
        Page<Test1> page = test1Repository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(test1Mapper::toDto));
    }

    @Override
    @Cacheable
    public List<Test1DTO> queryAll(Test1QueryCriteria criteria){
        return test1Mapper.toDto(test1Repository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public Test1DTO findById(Integer id) {
        Test1 test1 = test1Repository.findById(id).orElseGet(Test1::new);
        ValidationUtil.isNull(test1.getId(),"Test1","id",id);
        return test1Mapper.toDto(test1);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public Test1DTO create(Test1 resources) {
        return test1Mapper.toDto(test1Repository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Test1 resources) {
        Test1 test1 = test1Repository.findById(resources.getId()).orElseGet(Test1::new);
        ValidationUtil.isNull( test1.getId(),"Test1","id",resources.getId());
        test1.copy(resources);
        test1Repository.save(test1);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        test1Repository.deleteById(id);
    }


    @Override
    public void download(List<Test1DTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Test1DTO test1 : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("名称", test1.getName());
            map.put("备注", test1.getRemark());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}