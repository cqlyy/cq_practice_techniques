package cq.techniques;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = DocumentApplication.class)
@RunWith(SpringRunner.class)
public class TestFreeMarker {

    @Test
    public void freeMarker() throws URISyntaxException, IOException, TemplateException {
        // 创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 设置模板路径 toURI()防止路径出现空格
        String classpath = this.getClass().getResource("/").toURI().getPath();
        configuration.setDirectoryForTemplateLoading(new File(classpath+"/templates/"));
        // 设置字符集
        configuration.setDefaultEncoding("utf-8");
        // 加载模板
        Template template = configuration.getTemplate("demo.ftl");
        // 数据模型
        Map<String,Object> map = new HashMap<>();
        map.put("name", "静态化测试3");
        // 静态化
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
        // 打印静态化内容
        System.out.println(content);
        InputStream inputStream = IOUtils.toInputStream(content);
        creatFile("D:/cqlyy/project/cq/cq_practice_techniques_parent/cq_techniques_document/target/static/html/","demo3.html");
        // 输出文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/cqlyy/project/cq/cq_practice_techniques_parent/cq_techniques_document/target/static/html/demo3.html"));
        int copy = IOUtils.copy(inputStream, fileOutputStream);
        System.out.println(copy);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(fileOutputStream);
    }

    public void creatFile(String filePath, String fileName) {
        File folder = new File(filePath);
        //文件夹路径不存在
        if (!folder.exists() && !folder.isDirectory()) {
            System.out.println("文件夹路径不存在，创建路径:" + filePath);
            folder.mkdirs();
        } else {
            System.out.println("文件夹路径存在:" + filePath);
        }

        // 如果文件不存在就创建
        File file = new File(filePath + fileName);
        if (!file.exists()) {
            System.out.println("文件不存在，创建文件:" + filePath + fileName);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件已存在，文件为:" + filePath + fileName);
        }
    }
}
