package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.Test2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
public interface Test2Repository extends JpaRepository<Test2, Integer>, JpaSpecificationExecutor<Test2> {
}