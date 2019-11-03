package me.zhengjie.myTest.service;

import me.zhengjie.myTest.domain.MyTest;
import me.zhengjie.myTest.service.dto.MyTestDTO;
import me.zhengjie.myTest.service.dto.MyTestQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
public interface MyTestService {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(MyTestQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<MyTestDTO>
    */
    List<MyTestDTO> queryAll(MyTestQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return MyTestDTO
     */
    MyTestDTO findById(Integer id);

    MyTestDTO create(MyTest resources);

    void update(MyTest resources);

    void delete(Integer id);

    void download(List<MyTestDTO> all, HttpServletResponse response) throws IOException;
}