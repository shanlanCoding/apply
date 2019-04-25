package cn.gobyte.apply.service.user.impl;

import cn.gobyte.apply.dao.user.CourseMapper;
import cn.gobyte.apply.pojo.user.Course;
import cn.gobyte.apply.service.BaseService;
import cn.gobyte.apply.service.user.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CourseServiceImpl extends BaseService<Course> implements CourseService {
    @Autowired
    private CourseMapper cm;


    // 根据专业名称，查询考试科目，以及考试的时间
    @Override
    public Course selectExamCourseByMajor(String name) {
        Example example = new Example(Course.class);
        example.createCriteria().andCondition("cname=", name);
        // 开始查数据库
        List<Course> courseList = this.selectByExample(example);
        return courseList.isEmpty() ? null : courseList.get(0);
    }
}
