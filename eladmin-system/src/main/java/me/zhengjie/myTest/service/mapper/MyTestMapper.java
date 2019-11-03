package me.zhengjie.myTest.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.myTest.domain.MyTest;
import me.zhengjie.myTest.service.dto.MyTestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MyTestMapper extends BaseMapper<MyTestDTO, MyTest> {

}