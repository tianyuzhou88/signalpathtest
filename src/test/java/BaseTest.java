import core.Base;
import core.ultils.LinkUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseTest extends Base {

    @Test
    public void linkTest(){
       // webDriver.get("http://www.google.com");
        Map<Boolean, List<String>> map= webDriver.findElements(By.xpath("//*[@href]"))  // find all elements which has href attribute
                .stream()
                .map(ele -> ele.getAttribute("href"))   // get the value of href
                .map(String::trim)                      // trim the text
                .distinct()                             // there could be duplicate links , so find unique
                .collect(Collectors.partitioningBy(link -> LinkUtil.getResponseCode(link) == 200));

        map.get(false)
                .forEach(System.out::println);
    }



}
