package cn.gobyte.apply.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * TK-Mybatis需要该类
*/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
	
}