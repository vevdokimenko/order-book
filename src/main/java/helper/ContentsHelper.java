package helper;

import entity.OrderBookItem;

public class ContentsHelper {
    private final String[] lines;
    private final OrderBookHelper orderBookHelper = OrderBookHelper.getInstance();

    public ContentsHelper(String lines) {
        this.lines = lines.split("\n");
    }

    public void runCommands() {
        for (String line : lines) {
            String[] params = line.split(orderBookHelper.COMMA);
            if (params.length < 2 || params.length > 4) {
                continue;
            }

            switch (params[0]) {
                case "u":
                    try {
                        orderBookHelper.addItem(new OrderBookItem(
                                Integer.parseInt(params[1]),
                                Integer.parseInt(params[2]),
                                params[3]
                        ));
                    } catch (Exception e) {
                        break;
                    }
                    break;
                case "q":
                    if (params.length == 2) orderBookHelper.query(params[1], null);
                    if (params.length == 3) orderBookHelper.query(params[1], params[2]);
                    break;
                case "o":
                    orderBookHelper.order(params[1], params[2]);
                    break;
                default:
                    break;
            }
        }
    }
}
