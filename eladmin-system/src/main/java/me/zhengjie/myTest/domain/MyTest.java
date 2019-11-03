package me.zhengjie.myTest.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
@Entity
@Data
@Table(name="my_test")
public class MyTest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "remark")
    private String remark;

    @Column(name = "data_scope")
    private String dataScope;

    @Column(name = "level")
    private Integer level;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "permission")
    private String permission;

    public void copy(MyTest source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}