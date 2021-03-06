package cn.gobyte.apply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 也是抄的
 */

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

/*    public BaseService(Mapper<T> mapper) {
        this.mapper = mapper;
    }*/

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * TODO: 插入对象到数据库
     *
     * @param entity
     * @return int: 
     * @author shanLan misterchou@qq.com
     * @date 2019/4/25 20:57
     */
    @Override
    @Transactional
    public int save(T entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<T> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    /**
     * TODO: 更新非空;通过主键选择更新
     *
     * @param entity
     * @return int:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/25 20:24
     */
    @Override
    @Transactional
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    /**
    * TODO: 根据条件查询
    *
    * @param example 条件
    * @return java.util.List<T>:
    * @author shanLan misterchou@qq.com
    * @date 2019/4/25 20:25
    */
    @Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

}
