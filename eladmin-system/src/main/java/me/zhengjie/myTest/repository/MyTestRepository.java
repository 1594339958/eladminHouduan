package me.zhengjie.myTest.repository;

import me.zhengjie.myTest.domain.MyTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
public interface MyTestRepository extends JpaRepository<MyTest, Integer>, JpaSpecificationExecutor<MyTest> {
}