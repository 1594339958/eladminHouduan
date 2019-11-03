package me.zhengjie.myTest.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author Zheng Jie
* @date 2019-11-03
*/
@Data
public class MyTestDTO implements Serializable {

    private Integer id;

    private String name;

    private String remark;

    private String dataScope;

    private Integer level;

    private Timestamp createTime;

    private String permission;
}