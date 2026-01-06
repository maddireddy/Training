package ioPackage.zipexamples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressZipFile {
    public static void main(String[] args) {

        try (FileOutputStream fos = new FileOutputStream("src_sample.zip");
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream in = new FileInputStream("sample.txt");) {
            ZipEntry ze = new ZipEntry("sample.txt");
            zos.putNextEntry(ze);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
