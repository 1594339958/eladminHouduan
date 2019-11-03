package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.Test2;
import me.zhengjie.modules.system.service.dto.Test2DTO;
import me.zhengjie.modules.system.service.dto.Test2QueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
public interface Test2Service {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(Test2QueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<Test2DTO>
    */
    List<Test2DTO> queryAll(Test2QueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return Test2DTO
     */
    Test2DTO findById(Integer id);

    Test2DTO create(Test2 resources);

    void update(Test2 resources);

    void delete(Integer id);

    void download(List<Test2DTO> all, HttpServletResponse response) throws IOException;
}