package loggerLearning;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class LoggerLearning {
    //static Logger logger = Logger.getLogger(LoggerLearning.class);

    private static final Logger logger = LogManager.getLogger(LoggerLearning.class);

    public static void main(String[] args) {
        logger.info("Inside Main");
        LoggerLearning loggerLearning = new LoggerLearning();
        try {
            loggerLearning.readFromFile();
        } catch (IOException e) {
            logger.debug(e);
            throw new RuntimeException(e);
        }
    }

    public void readFromFile() throws IOException {
        FileInputStream fileInputStream = null;
        Date date = null;
        try {
            File file = new File("Presentation on java preety1.pptx");
            fileInputStream = new FileInputStream(file);
            int count = fileInputStream.read();
            System.out.println(count);
            date = new Date();
            System.out.println(date);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            logger.error("Exception while reading the file", ioException);
            throw ioException;
        }

    }
}
