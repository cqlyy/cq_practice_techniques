package cq.techniques.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class FreeMarkerService {

    public void createHtmlByFreeMarker(){
        // 创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 设置模板路径 toURI()防止路径出现空格
        String classpath = null;
        try {
            classpath = this.getClass().getResource("/").toURI().getPath();
        } catch (URISyntaxException e) {
            log.error("设置模板路径异常：",e);
        }
        try {
            configuration.setDirectoryForTemplateLoading(new File(classpath+"/templates/"));
        } catch (IOException e) {
            log.error("配置模板路径异常：",e);
        }
        // 设置字符集
        configuration.setDefaultEncoding("utf-8");
        // 加载模板
        Template template = null;
        try {
            template = configuration.getTemplate("demo.ftl");
        } catch (IOException e) {
            log.error("加载模板异常：",e);
        }

        // 数据模型
        Map<String,Object> map = new HashMap<>();
        map.put("name", "啊啊啊啊啊");

        // 静态化
        String content = null;
        try {
            content = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
        } catch (IOException | TemplateException e) {
            log.error("模板静态化异常：",e);
        }
        // 打印静态化内容
        System.out.println(content);
        InputStream inputStream = IOUtils.toInputStream(content);

        try {
            FileUtils.forceMkdir(new File("D:/cqlyy/project/cq/cq_practice_techniques_parent/cq_techniques_document/target/static/html"));
        } catch (IOException e) {
            log.error("创建文件夹或文件异常：",e);
        }

        // 输出文件
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(new File("D:/cqlyy/project/cq/cq_practice_techniques_parent/cq_techniques_document/target/static/html/demo3.html"));
        } catch (FileNotFoundException e) {
            log.error("找不到文件：",e);
        }

        try {
            int copy = IOUtils.copy(inputStream, fileOutputStream);
        } catch (IOException e) {
            log.error("输出文件异常：",e);
        }
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(fileOutputStream);
    }
}
