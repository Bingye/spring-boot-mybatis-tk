package com.bingye.config;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

//@RegisterMapper
public interface TkMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
