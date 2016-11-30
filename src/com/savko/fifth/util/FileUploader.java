package com.savko.fifth.util;

import java.io.*;

public class FileUploader {

    public static OutputStream upload(String filePath, InputStream fileContent) throws IOException {
        OutputStream out = new FileOutputStream(new File(filePath));
        int read;
        final byte[] bytes = new byte[1024];

        while ((read = fileContent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        return out;
    }

}
