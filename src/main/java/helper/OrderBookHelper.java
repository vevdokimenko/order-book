package helper;

import entity.OrderBook;
import entity.OrderBookItem;

import java.util.Comparator;

public class OrderBookHelper {
    private static OrderBookHelper INSTANCE;
    private final OrderBook orderBook = new OrderBook();
    private final StringBuilder output = new StringBuilder();
    private final String NEWLINE = "\n";
    public final String COMMA = ",";

    private OrderBookHelper() {
    }

    public static OrderBookHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderBookHelper();
        }
        return INSTANCE;
    }

    private OrderBookItem bestBid() {
        return orderBook.getBookItems().stream()
                .filter(bid -> bid.getType().equals("bid"))
                .filter(bid -> bid.getSize() > 0)
                .max(Comparator.comparingInt(OrderBookItem::getPrice))
                .orElse(null);
    }

    private OrderBookItem bestAsk() {
        return orderBook.getBookItems().stream()
                .filter(ask -> ask.getType().equals("ask"))
                .filter(ask -> ask.getSize() > 0)
                .min(Comparator.comparingInt(OrderBookItem::getPrice))
                .orElse(null);
    }

    private int getSizeByPrice(String priceStr) {
        try {
            int price = Integer.parseInt(priceStr);
            OrderBookItem filtered =
                    orderBook.getBookItems().stream()
                            .filter((o) -> o.getPrice() == price)
                            .findFirst()
                            .orElse(null);
            return filtered != null ? filtered.getSize() : -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public void order(String o, String size) {
        switch (o) {
            case "buy":
                if (bestAsk().getSize() >= Integer.parseInt(size)) {
                    bestAsk().setSize(bestAsk().getSize() - Integer.parseInt(size));
                } else {
                    bestAsk().setSize(0);
                }
                break;
            case "sell":
                if (bestBid().getSize() >= Integer.parseInt(size)) {
                    bestBid().setSize(bestBid().getSize() - Integer.parseInt(size));
                } else {
                    bestBid().setSize(0);
                }
                break;
            default:
                break;
        }
    }

    public void addItem(OrderBookItem item) {
        if (orderBook.getBookItems().contains(item)) {
            int indexOfEl = orderBook.getBookItems().indexOf(item);
            orderBook.getBookItems().get(indexOfEl).setSize(item.getSize());
        } else {
            orderBook.getBookItems().add(item);
        }
    }

    public String query(String q, String price) {
        switch (q) {
            case "best_bid":
                return bestItem(bestBid());
            case "best_ask":
                return bestItem(bestAsk());
            case "size":
                int size = getSizeByPrice(price);
                return (size != -1)
                        ? output
                        .append(size)
                        .append(NEWLINE)
                        .toString()
                        : null;
            default:
                return null;
        }
    }

    private String bestItem(OrderBookItem item) {
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
