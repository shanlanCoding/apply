package cn.gobyte.apply.service.user;

import cn.gobyte.apply.pojo.user.Course;
import cn.gobyte.apply.service.IService;
import org.springframework.stereotype.Service;

/**
 * TODO: 根据专业获取考试科目
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/24 1:42
 */

@Service
public interface CourseService extends IService<Course> {

    /**
     * TODO: 根据专业名称，查询考试科目，以及考试的时间
     *
     * @param name 报考的专业，如：计算技术与科学
     * @return cn.gobyte.apply.pojo.user.Course:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/24 3:01
     */
    Course selectExamCourseByMajor(String name);

}
