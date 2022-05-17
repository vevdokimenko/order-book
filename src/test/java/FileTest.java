import helper.FileHelper;
import org.junit.Assert;
import org.junit.Test;

public class FileTest {
    @Test
    public void fileTest() {
        FileHelper fileHelper = new FileHelper("input.txt", "output.txt");
        Assert.assertNotNull(fileHelper.getFileContents());
        Assert.assertTrue(fileHelper.getFileContents().length() > 0);
    }

    @Test(timeout = 300)
    public void testTimeout() {
        Main.main(new String[]{""});
    }
}
