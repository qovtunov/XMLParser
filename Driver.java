import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by kovtunov on 19/12/14.
 */
public class Driver {
    static void launchDriver() {

        WebDriver ffDriver = new FirefoxDriver();
        System.out.println("[Launching ffDriver...]");
        ffDriver.get("http://gtmetrix.com/");
        System.out.println("[FireFox is opened]\n");

    }
}
