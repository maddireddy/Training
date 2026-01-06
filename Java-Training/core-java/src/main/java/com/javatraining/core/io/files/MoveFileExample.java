package ioPackage.fileoperations.fileexamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MoveFileExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoveFileExample.class);

    public static void main(String[] args) {
        moveFile();
    }

    public static void moveFile() {
        File file = new File("C:/workspace/java-io-guide/sample.txt");
        boolean hasRename = file.renameTo(new File("C:/workspace/java-io-guide/src/sample.txt"));
        if (hasRename) {
            LOGGER.info("File is moved successful!");
        } else {
            LOGGER.info("File is failed to move!");
        }
    }
}
