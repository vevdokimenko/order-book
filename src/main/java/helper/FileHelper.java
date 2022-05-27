package helper;

import java.io.*;

public class FileHelper {
    Command command = new Command();
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

    public void readInputFile() {
        try (BufferedReader in = new BufferedReader(new FileReader(dir + inputFilePath))) {
            String line;
            while ((line = in.readLine()) != null) {
                // process line here.
                command.runCommand(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeOutputFile() {
        try (FileWriter fileWriter = new FileWriter(dir + outputFilePath)) {
            fileWriter.write(command.getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
