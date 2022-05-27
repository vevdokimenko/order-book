import helper.FileHelper;

public class Main {
    public static void main(String[] args) {
        FileHelper fileHelper = new FileHelper("input.txt", "output.txt");

        fileHelper.readInputFile();
        fileHelper.writeOutputFile();
    }
}
