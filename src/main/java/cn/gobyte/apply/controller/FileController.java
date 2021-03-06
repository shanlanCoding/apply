package cn.gobyte.apply.controller;

import cn.gobyte.apply.exception.FileDownloadException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TODO: 文件控制器
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/8 13:47
 */
@Controller
public class FileController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * TODO: 文件下载
     *
     * @param fileName
     * @param delete
     * @param response
     * @return void:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/5 16:17
     */
    @RequestMapping("file/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response) throws IOException, FileDownloadException {
        /*
         * fileName=8c28d2d5-5a93-4e39-890b-978e1e066b1d_%E7%94%A8%E6%88%B7%E8%A1%A8.xlsx&
         * delete=true
         * */
//        System.err.println("fileDownload====" + fileName + "----" + this.getClass().getName());
        if (StringUtils.isNotBlank(fileName) && !fileName.endsWith(".xlsx") && !fileName.endsWith(".csv")) {
            throw new FileDownloadException("不支持该类型文件下载");
        }
        String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf('_') + 1);
        String filePath = "file/" + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileDownloadException("文件未找到");
        }

        response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(realFileName, "utf-8"));
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");

        try (InputStream inputStream = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
            log.error("文件下载失败", e);
        } finally {
            if (delete && file.exists()) {
                Files.delete(Paths.get(filePath));
            }
        }
    }
}
