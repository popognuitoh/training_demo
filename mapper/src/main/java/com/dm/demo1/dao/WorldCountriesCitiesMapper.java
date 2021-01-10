package com.dm.demo1.dao;

import com.dm.demo1.entity.WorldCountriesCities;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-07-31
 */
@Component
@Mapper
public interface WorldCountriesCitiesMapper extends BaseMapper<WorldCountriesCities> {

}
