package me.zhengjie.Test1.service;

import me.zhengjie.Test1.domain.Test1;
import me.zhengjie.Test1.service.dto.Test1DTO;
import me.zhengjie.Test1.service.dto.Test1QueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
public interface Test1Service {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(Test1QueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<Test1DTO>
    */
    List<Test1DTO> queryAll(Test1QueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return Test1DTO
     */
    Test1DTO findById(Integer id);

    Test1DTO create(Test1 resources);

    void update(Test1 resources);

    void delete(Integer id);

    void download(List<Test1DTO> all, HttpServletResponse response) throws IOException;
}