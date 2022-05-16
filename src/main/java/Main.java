import helper.ContentsHelper;
import helper.FileHelper;
import helper.OrderBookHelper;

public class Main {
    public static void main(String[] args) {
//        Start time
        long startTime = System.currentTimeMillis();

        FileHelper fileHelper = new FileHelper("input.txt", "output.txt");
        OrderBookHelper orderBookHelper = OrderBookHelper.getInstance();

        ContentsHelper contentsHelper = new ContentsHelper(fileHelper.getFileContents());
        contentsHelper.runCommands();

        fileHelper.writeOutputFile(orderBookHelper.getOutput());

//        Finish time
        System.out.println(orderBookHelper.getOutput());
        System.out.println("\n============================================");
        System.out.printf("[%d ms]", System.currentTimeMillis() - startTime);
    }
}
