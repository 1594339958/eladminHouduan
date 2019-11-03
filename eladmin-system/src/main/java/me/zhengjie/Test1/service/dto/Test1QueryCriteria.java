package me.zhengjie.Test1.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
@Data
public class Test1QueryCriteria{

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String name;
}