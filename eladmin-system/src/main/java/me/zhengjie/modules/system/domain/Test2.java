package me.zhengjie.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
@Entity
@Data
@Table(name="test2")
public class Test2 implements Serializable {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 名称
    @Column(name = "name")
    private String name;

    // 备注
    @Column(name = "remark")
    private String remark;

    public void copy(Test2 source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}