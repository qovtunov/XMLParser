import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by kovtunov on 18/12/14.
 */
public class PageNotFoundCheck {

    void pageNotFoundCheck() throws InterruptedException {

        int notFound = 0;

        WebDriver ffDriver = new FirefoxDriver();
        System.out.println("[Launching ffDriver...]");

        for (String i : XmlReader.urls) { //String i : XmlReader.urls StringBuffer i : XmlReader.mobileUrls
            String s = i.toString();
            ffDriver.get(s);
            String report = ffDriver.getCurrentUrl();
            System.out.println(i + " -----------> " + report);

            if (report.equals("http://stage-dev.appery.io/mobile/?page-not-found")) { // http://appery.io/mobile/?page-not-found
                notFound++;                                                // http://stage-dev.appery.io/mobile/?page-not-found
                System.out.println("404\n");
            }
        }

        System.out.println("404: " + notFound);

        if (ffDriver != null) {
            ffDriver.close();
            ffDriver.quit();
        }

    }

}
