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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    @Log("修改单个用户成绩")
    @RequestMapping("/grade/edit")
    @PreAuthorize("hasAuthority('grade:edit')")
    @ResponseBody
    public ResponseBo editGrade(userGrade user) {
        try {
            //        System.err.println("userGrade====" + user);
            ResponseBo responseBo = this.GradeService.editGrade(user);
            return responseBo;
        } catch (Exception e) {
            log.error("修改成绩信息失败", e);
            return ResponseBo.error("修改成绩失败，请联系管理员.");
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

    /**
     * TODO: 上传表格，解析数据
     *
     * @param file
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/8 18:18
     */
    @RequestMapping("/grade/uploadFile")
    @ResponseBody
    public ResponseBo uploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 这里只是简单例子，文件直接输出到项目路径下。
                // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
                // 还有关于文件格式限制、文件大小限制，详见：中配置。
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                log.error("上传失败", e);
                return ResponseBo.error("上传失败:" + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                log.error("上传失败", e);
                return ResponseBo.error("上传失败:" + e.getMessage());
            }
            return ResponseBo.ok("文件上传成功");
        } else {
            return ResponseBo.error("上传失败，因为文件是空的.");
        }

    }

}
