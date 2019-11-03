package me.zhengjie.Test1.repository;

import me.zhengjie.Test1.domain.Test1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
public interface Test1Repository extends JpaRepository<Test1, Integer>, JpaSpecificationExecutor<Test1> {
}