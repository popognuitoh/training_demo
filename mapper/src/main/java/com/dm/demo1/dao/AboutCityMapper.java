package com.dm.demo1.dao;

import com.dm.demo1.entity.AboutCity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 关于城市 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-08-10
 */
@Component
@Mapper
public interface AboutCityMapper extends BaseMapper<AboutCity> {

}
