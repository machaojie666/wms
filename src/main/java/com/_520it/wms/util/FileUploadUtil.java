package com._520it.wms.util;

import java.io.File;
import java.util.UUID;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class FileUploadUtil {
    public static final String suffix = "_small";

    public static String uploadFile(File file, String fileName) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        fileName = uuid + fileType;
        String path = ServletActionContext.getServletContext().getRealPath("/upload");

        File targetFile = new File(path, fileName);
        FileUtils.copyFile(file, targetFile);

        String smallImg = uuid + "_small" + fileType;
        File smallTargetFile = new File(path, smallImg);

        Thumbnails.of(new File[]{targetFile}).scale(0.4D).toFile(smallTargetFile);
        return "/upload/" + fileName;
    }

    public static void deleteFile(String imagePath) {
        String path = ServletActionContext.getServletContext().getRealPath("/") + imagePath;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        path = ServletActionContext.getServletContext().getRealPath("/")
                + imagePath.substring(0, imagePath.indexOf(".")) + "_small"
                + imagePath.substring(imagePath.indexOf("."));
        file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
}
