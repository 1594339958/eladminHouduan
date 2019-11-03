package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.system.domain.Test2;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.system.repository.Test2Repository;
import me.zhengjie.modules.system.service.Test2Service;
import me.zhengjie.modules.system.service.dto.Test2DTO;
import me.zhengjie.modules.system.service.dto.Test2QueryCriteria;
import me.zhengjie.modules.system.service.mapper.Test2Mapper;
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
@CacheConfig(cacheNames = "test2")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class Test2ServiceImpl implements Test2Service {

    private final Test2Repository test2Repository;

    private final Test2Mapper test2Mapper;

    public Test2ServiceImpl(Test2Repository test2Repository, Test2Mapper test2Mapper) {
        this.test2Repository = test2Repository;
        this.test2Mapper = test2Mapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(Test2QueryCriteria criteria, Pageable pageable){
        Page<Test2> page = test2Repository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(test2Mapper::toDto));
    }

    @Override
    @Cacheable
    public List<Test2DTO> queryAll(Test2QueryCriteria criteria){
        return test2Mapper.toDto(test2Repository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public Test2DTO findById(Integer id) {
        Test2 test2 = test2Repository.findById(id).orElseGet(Test2::new);
        ValidationUtil.isNull(test2.getId(),"Test2","id",id);
        return test2Mapper.toDto(test2);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public Test2DTO create(Test2 resources) {
        return test2Mapper.toDto(test2Repository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Test2 resources) {
        Test2 test2 = test2Repository.findById(resources.getId()).orElseGet(Test2::new);
        ValidationUtil.isNull( test2.getId(),"Test2","id",resources.getId());
        test2.copy(resources);
        test2Repository.save(test2);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        test2Repository.deleteById(id);
    }


    @Override
    public void download(List<Test2DTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Test2DTO test2 : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("名称", test2.getName());
            map.put("备注", test2.getRemark());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}