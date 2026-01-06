package ioPackage.fileoperations.fileexamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class DeleteFileExample {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DeleteFileExample.class);

    public static void main(String[] args) {
        deleteFile();
    }

    public static void deleteFile() {
        File file = new File("C:/workspace/sample.txt");
        if (file.delete()) {
            LOGGER.info(file.getName() + "created !!");
        } else {
            LOGGER.info("Delete operation failed");
        }
    }
}
