package me.zhengjie.myTest.service.impl;

import me.zhengjie.myTest.domain.MyTest;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.myTest.repository.MyTestRepository;
import me.zhengjie.myTest.service.MyTestService;
import me.zhengjie.myTest.service.dto.MyTestDTO;
import me.zhengjie.myTest.service.dto.MyTestQueryCriteria;
import me.zhengjie.myTest.service.mapper.MyTestMapper;
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
@CacheConfig(cacheNames = "myTest")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MyTestServiceImpl implements MyTestService {

    private final MyTestRepository myTestRepository;

    private final MyTestMapper myTestMapper;

    public MyTestServiceImpl(MyTestRepository myTestRepository, MyTestMapper myTestMapper) {
        this.myTestRepository = myTestRepository;
        this.myTestMapper = myTestMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(MyTestQueryCriteria criteria, Pageable pageable){
        Page<MyTest> page = myTestRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(myTestMapper::toDto));
    }

    @Override
    @Cacheable
    public List<MyTestDTO> queryAll(MyTestQueryCriteria criteria){
        return myTestMapper.toDto(myTestRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public MyTestDTO findById(Integer id) {
        MyTest myTest = myTestRepository.findById(id).orElseGet(MyTest::new);
        ValidationUtil.isNull(myTest.getId(),"MyTest","id",id);
        return myTestMapper.toDto(myTest);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public MyTestDTO create(MyTest resources) {
        return myTestMapper.toDto(myTestRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(MyTest resources) {
        MyTest myTest = myTestRepository.findById(resources.getId()).orElseGet(MyTest::new);
        ValidationUtil.isNull( myTest.getId(),"MyTest","id",resources.getId());
        myTest.copy(resources);
        myTestRepository.save(myTest);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        myTestRepository.deleteById(id);
    }


    @Override
    public void download(List<MyTestDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MyTestDTO myTest : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" name",  myTest.getName());
            map.put(" remark",  myTest.getRemark());
            map.put(" dataScope",  myTest.getDataScope());
            map.put(" level",  myTest.getLevel());
            map.put(" createTime",  myTest.getCreateTime());
            map.put(" permission",  myTest.getPermission());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}