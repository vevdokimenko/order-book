package helper;

import java.io.*;
import java.util.Arrays;

public class FileHelper {
    private final String inputFilePath;
    private final String outputFilePath;
    private final String dir;

    public FileHelper(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.dir = findInputFileDir();
    }

    private String findInputFileDir() {
        return
                (System.getProperty("user.dir") != null)
                        ? System.getProperty("user.dir").concat("/")
                        : null;
    }

    public String getFileContents() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(dir + inputFilePath)) {
            while (fileReader.ready()) {
                int value = fileReader.read();
                stringBuilder.append((char) value);
            }
        } catch (IOException e) {
            System.exit(-1);
        }
        return stringBuilder.toString();
    }

    public void writeOutputFile(String contents) {
        try (FileWriter fileWriter = new FileWriter(dir + outputFilePath)) {
            fileWriter.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
