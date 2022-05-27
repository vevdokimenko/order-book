import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitTest {
    @Test//(timeout = 300)
    public void executionTime() {
        Main.main(new String[0]);
    }
    @Test
    public void a_bestBidTest() {
//        OrderBookHelper orderBookHelper = OrderBookHelper.getInstance();
//        orderBookHelper.addItem(new OrderBookItem(10, 5, "bid"));
//        orderBookHelper.addItem(new OrderBookItem(10, 3, "bid"));
//        Assert.assertEquals("10,3\n", orderBookHelper.query("best_bid",0));
    }

    @Test
    public void b_getSizeOfNonExistsPrice() {
//        OrderBookHelper orderBookHelper = OrderBookHelper.getInstance();
//        orderBookHelper.addItem(new OrderBookItem(10, 5, "bid"));
//        Assert.assertEquals(null, orderBookHelper.query("size", 123));
    }

    @Test
    public void c_getSizeByPrice() {
//        OrderBookHelper orderBookHelper = OrderBookHelper.getInstance();
//        orderBookHelper.addItem(new OrderBookItem(10, 5, "bid"));
//        Assert.assertEquals("5\n", orderBookHelper.query("size", 10));
    }
}
