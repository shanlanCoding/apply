package cn.gobyte.apply.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 抄的，提供了很多功能
*/
@Service
public interface IService<T> {

    /**
     * TODO: 查询所有
     *
     * @param
     * @return java.util.List<T>:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/13 21:30
     */
    List<T> selectAll();

    /**
     * TODO: 根据key查询
     *
     * @param key
     * @return T: 返回泛型
     * @author shanLan misterchou@qq.com
     * @date 2019/4/13 21:29
     */
    T selectByKey(Object key);

    /**
     * TODO: 保存
     *
     * @param entity
     * @return int:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/13 21:29
     */
    int save(T entity);

    /**
     * TODO: 删除
     *
     * @param key
     * @return int:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/13 21:29
     */
    int delete(Object key);

    /**
     * TODO: 批量删除
     *
     * @param list
     * @param property
     * @param clazz
     * @return int:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/13 21:29
     */
    int batchDelete(List<String> list, String property, Class<T> clazz);

    /**
     * TODO: 更新所有
     *
     * @param entity
     * @return int:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/13 21:29
     */
    int updateAll(T entity);

    /**
     * TODO: 更新非空
     *
     * @param entity
     * @return int:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/13 21:28
     */
    int updateNotNull(T entity);

    /**
     * TODO: 根据条件查询记录集
     *
     * @param example 条件筛选;准确的说这里应该使用：tk.mybatis.mapper.entity.Example对象来作为参数
     * @return java.util.List<T>:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/13 21:27
     */
    List<T> selectByExample(Object example);
}