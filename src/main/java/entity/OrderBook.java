package entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderBook {
    private final List<OrderBookItem> bookItems = new ArrayList<>();
}
