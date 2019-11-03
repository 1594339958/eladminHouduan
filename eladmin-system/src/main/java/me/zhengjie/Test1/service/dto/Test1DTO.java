package me.zhengjie.Test1.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author Zheng Jie
* @date 2019-11-03
*/
@Data
public class Test1DTO implements Serializable {

    // ID
    private Integer id;

    // 名称
    private String name;

    // 备注
    private String remark;
}