package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class HttpStatusImageDownloader {

    private final HttpStatusChecker checker = new HttpStatusChecker();

    public void downloadStatusImage(int code) {

        try {
            String imageUrl = checker.getStatusImage(code);
            URL url = new URL(imageUrl);

            String dirPath = "src/main/java/images";
            File dir = new File(dirPath);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filePath = dirPath + "/status_" + code + ".jpg";

            try (InputStream in = url.openStream();
                 FileOutputStream out = new FileOutputStream(filePath)) {

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("Завантажується файл: status_" + code + ".jpg");

        } catch (Exception e) {
            throw new RuntimeException("Cannot download image for status: " + code, e);
        }
    }
}