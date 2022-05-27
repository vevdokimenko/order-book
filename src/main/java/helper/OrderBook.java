package helper;

import entity.BookItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderBook {
    private static OrderBook INSTANCE;
    private final List<BookItem> orderBook = new ArrayList<>();
    private final StringBuilder output = new StringBuilder();
    private final String NEWLINE = "\n";
    public final String COMMA = ",";

    private OrderBook() {
    }

    public static OrderBook getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderBook();
        }
        return INSTANCE;
    }

    private BookItem bestBid() {
        return orderBook.stream()
                .filter(bid -> bid.getType().equals("bid"))
                .filter(bid -> bid.getSize() > 0)
                .max(Comparator.comparingInt(BookItem::getPrice))
                .orElse(null);
    }

    private BookItem bestAsk() {
        return orderBook.stream()
                .filter(ask -> ask.getType().equals("ask"))
                .filter(ask -> ask.getSize() > 0)
                .min(Comparator.comparingInt(BookItem::getPrice))
                .orElse(null);
    }

    private int getSizeByPrice(int price) {
        try {
            return orderBook.stream()
                    .filter((o) -> o.getPrice() == price)
                    .findFirst()
                    .orElse(null).getSize();
        } catch (Exception e) {
            return 0;
        }
    }

    public void order(String o, String size) throws NumberFormatException {
        int orderSize = Integer.parseInt(size);

        switch (o) {
            case "buy":
            case "sell":
                trading(orderSize, o);
                break;
            default:
                break;
        }
    }

    private void trading(int orderSize, String orderType) {
        while (orderSize != 0) {
            BookItem item = (orderType.equals("buy")) ? bestAsk() : bestBid();
            int bestBookSize = item.getSize();

            if (bestBookSize >= orderSize) {
                item.setSize(bestBookSize - orderSize);
                orderSize = 0;
            } else {
                orderSize -= bestBookSize;
                item.setSize(0);
            }
        }
    }

    public void addItem(BookItem item) {
        if (orderBook.contains(item)) {
            int indexOfEl = orderBook.indexOf(item);
            orderBook.get(indexOfEl).setSize(item.getSize());
        } else {
            orderBook.add(item);
        }
    }

    public String query(String q, int price) {
        switch (q) {
            case "best_bid":
                return bestItem(bestBid());
            case "best_ask":
                return bestItem(bestAsk());
            case "size":
                return output
                        .append(getSizeByPrice(price))
                        .append(NEWLINE)
                        .toString();
            default:
                return null;
        }
    }

    private String bestItem(BookItem item) {
        if (item == null) return output.toString();
        return output
                .append(item.getPrice())
                .append(COMMA)
                .append(item.getSize())
                .append(NEWLINE)
                .toString();
    }

    public String getOutput() {
        String result = output.toString();
        if (result.length() > 2) result = result.substring(0, result.length() - 1);
        return result;
    }
}
