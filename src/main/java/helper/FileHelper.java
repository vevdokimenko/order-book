package helper;

import lombok.AllArgsConstructor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@AllArgsConstructor
public class FileHelper {
    private String inputFilePath;
    private String outputFilePath;

    public String getFileContents() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(inputFilePath)) {
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
        try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
            fileWriter.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
