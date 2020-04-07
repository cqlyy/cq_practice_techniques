package cq.techniques.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class FileUtils {
    /**
     * 如果文件夹或文件不存在，则先创建 If the folder or file does not exist, create it first
     * @param filePath 文件夹路径 folder path
     * @param fileName 文件名称 file name
     */
    public static void creatFile(String filePath, String fileName) {
        File folder = new File(filePath);
        //文件夹路径不存在
        if (!folder.exists() && !folder.isDirectory()) {
            log.info("文件夹路径不存在，创建路径:" + filePath);
            folder.mkdirs();
        } else {
            log.info("文件夹路径存在:" + filePath);
        }

        // 如果文件不存在就创建
        File file = new File(filePath + fileName);
        if (!file.exists()) {
            log.info("文件不存在，创建文件:" + filePath + fileName);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.info("文件已存在，文件为:" + filePath + fileName);
        }
    }
}
