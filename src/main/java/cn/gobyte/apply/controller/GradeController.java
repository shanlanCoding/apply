package cn.gobyte.apply.controller;

import cn.gobyte.apply.annotation.Log;
import cn.gobyte.apply.domain.QueryRequest;
import cn.gobyte.apply.domain.ResponseBo;
import cn.gobyte.apply.pojo.user.userGrade;
import cn.gobyte.apply.service.user.GradeService;
import cn.gobyte.apply.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class GradeController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GradeService GradeService;

    /**
     * TODO: 成绩列表html
     *
     * @param model
     * @return java.lang.String:
     * @author shanLan misterchou@qq.com
     * @date 2019/5/7 17:21
     */
    @RequestMapping("/grade")
    public String index(Model model) {
        return "system/grade/grade";
    }

    /**
     * 1
     * TODO:返回用户成绩列表
     *
     * @param request
     * @param user
     * @return java.util.Map<java.lang.String, java.lang.Object>:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/7 15:17
     */
    @Log("获取用户成绩列表")
    @RequestMapping("/grade/list")
    @PreAuthorize("hasAuthority('grade:list')")
    @ResponseBody
    public Map<String, Object> gradeList(QueryRequest request, userGrade user) {

//        System.err.println("userGrade====" + user);
        // 调用父类方法，根据页面数字大小查询
        return super.selectByPageNumSize(request, () -> this.GradeService.selectGradeAll(user));
    }

    @Log("获取单个用户成绩")
    @RequestMapping("/grade/getGrade")
    @PreAuthorize("hasAuthority('grade:list')")
    @ResponseBody
    public ResponseBo getGrade(QueryRequest request, userGrade user) {
        try {
            //        System.err.println("userGrade====" + user);
            userGrade grade = this.GradeService.selectGrade(user.getId());
            return ResponseBo.ok(grade);
        } catch (Exception e) {
            log.error("获取成绩信息失败", e);
            return ResponseBo.error("查询成绩失败，请联系管理员.");
        }

    }

    /**
     * TODO: 导出表格，下载csv表格
     *
     * @param user
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/7 14:39
     */
    @RequestMapping("/grade/csv")
    @ResponseBody
    public ResponseBo userCsv(userGrade user) {
        try {
            List<userGrade> list = this.GradeService.selectGradeAll(user);
            return FileUtils.createCsv("成绩表", list, userGrade.class);
        } catch (Exception e) {
            log.error("导出成绩信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

}
