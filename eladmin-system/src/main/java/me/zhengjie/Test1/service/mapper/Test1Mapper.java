package me.zhengjie.Test1.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.Test1.domain.Test1;
import me.zhengjie.Test1.service.dto.Test1DTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-11-03
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Test1Mapper extends BaseMapper<Test1DTO, Test1> {

}