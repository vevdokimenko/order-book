import entity.OrderBookItem;
import helper.FileHelper;
import helper.OrderBookHelper;
import org.junit.Assert;
import org.junit.Test;

public class UnitTest {

    @Test
    public void fileTest() {
        FileHelper fileHelper = new FileHelper("input.txt", "output.txt");
        Assert.assertNotNull(fileHelper.getFileContents());
        Assert.assertTrue(fileHelper.getFileContents().length() > 0);
    }

    @Test
    public void bestBidTest() {
        OrderBookHelper orderBookHelper = OrderBookHelper.getInstance();
        orderBookHelper.addItem(new OrderBookItem(10, 5, "bid"));
        orderBookHelper.addItem(new OrderBookItem(10, 3, "bid"));
        Assert.assertEquals("10,3\n", orderBookHelper.query("best_bid",null));
    }

    @Test(timeout = 300)
    public void testTimeout() throws InterruptedException
    {
        Main.main(new String[]{""});
    }
}
