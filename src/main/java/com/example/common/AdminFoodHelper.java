package com.example.common;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AdminFoodHelper {

    public Map<String, String> uploadPicture(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uploadDir = req.getSession().getServletContext().getRealPath("image");

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        factory.setSizeThreshold(1024 * 1024);

        Map<String, String> result = new HashMap<>();

        try {
            List<FileItem> items = upload.parseRequest(req);

            for (FileItem item : items) {
                String oldFilename = item.getName();
                if (!item.isFormField()) {
                    //检查扩展名
                    String fileExt = oldFilename.substring(oldFilename.lastIndexOf(".") + 1).toLowerCase();
                    if ("jpg".equals(fileExt) || "png".equals(fileExt)) {
                        String filename = System.currentTimeMillis() + "." + fileExt;
                        File f = new File(uploadDir);
                        if (!f.exists()) {
                            f.mkdir();
                        }
                        String saveFilename = f + File.separator + filename;
                        InputStream is = item.getInputStream();
                        OutputStream fos = new FileOutputStream(saveFilename);
                        byte[] b = new byte[1024 * 1024];
                        for (int length; -1 != (length = is.read(b)); ) fos.write(b, 0, length);
                        fos.flush();
                        fos.close();
                        System.out.println(oldFilename + " is saved to " + saveFilename);

                        // use / as separator here, not \, database table uses / separator
                        result.put("foodImageURL", "image/" + filename);
                    } else {
                        System.out.println(oldFilename + " file ignored");
                    }
                } else {
                    System.out.println("field name: " + item.getFieldName());
                    String value = item.getString();
                    value = new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    System.out.println("field value: " + value);

                    result.put(item.getFieldName(), value);
                }
            }
            return result;
        } catch (FileUploadException e) {
            e.printStackTrace();
            return null;
        }
    }

}
