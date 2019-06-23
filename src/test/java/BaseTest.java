import core.Base;
import org.testng.annotations.Test;

public class BaseTest extends Base {

    @Test
    public void goodtest(){
        webDriver.get("http://www.google.com");
    }
}
