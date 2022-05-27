package helper;

import entity.BookItem;

public class Command {
    private final OrderBook orderBook = OrderBook.getInstance();

    public void runCommand(String line) {
        String[] params = line.split(orderBook.COMMA);

        if (params.length < 2 || params.length > 4) return;

        try {
            switch (params[0]) {
                case "u":
                    orderBook.addItem(new BookItem(
                            Integer.parseInt(params[1]),
                            Integer.parseInt(params[2]),
                            params[3]
                    ));
                    break;
                case "q":
                    if (params.length == 2) orderBook.query(params[1], 0);
                    if (params.length == 3) orderBook.query(params[1], Integer.parseInt(params[2]));
                    break;
                case "o":
                    orderBook.order(params[1], params[2]);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format! " + e.getMessage());
        }
    }

    public String getOutput() {
        return orderBook.getOutput();
    }
}
