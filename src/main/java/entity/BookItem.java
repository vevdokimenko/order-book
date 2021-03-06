package entity;

import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@ToString
public class BookItem {
    private int price;
    private int size;
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItem that = (BookItem) o;
        return price == that.price && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, type);
    }
}
