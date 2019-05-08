package cn.gobyte.apply;

import cn.gobyte.apply.dao.user.UserGradeMapper;
import cn.gobyte.apply.pojo.user.userGrade;
import cn.gobyte.apply.utils.EasyPoiUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplyApplicationTests {
    @Autowired
    private UserGradeMapper ugm;

    @Test
    public void contextLoads() {

    }

    @org.junit.jupiter.api.Test
    public void fun01() {

//        String filePath = "D:\\QQPCmgr\\Desktop\\easyExcel.csv";
        String filePath = "D:\\QQPCmgr\\Desktop\\easyExcel.xlsxsss";

//        List<userGrade> list = this.ugm.selectAllGrade(user);

        List<userGrade> userGrades = EasyPoiUtils.importExcel(filePath, 0, 1, userGrade.class);

        for (userGrade u : userGrades) {
            System.err.println(u.toString());
        }

//        com.example.easypoi.util.EasyPoiUtils.exportExcel(userGradeList, "我是标题", "0", userGrade.class, filePath,);
//        FileUtils.createExcelByPOIKit(filePath, list, userGrade.class);

//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"), userGrade.class, userGradeList);

//        FileUtils.createExcelByPOIKit("excelTest", userGradeList, userGrade.class);
    }

}
