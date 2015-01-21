import com.sun.tools.classfile.Exceptions_attribute;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by kovtunov on 13/12/14.
 */
public class GTmetrix {

    String currentLink;
    int j = 0;

    static List<String> compareUrls = new ArrayList<>();

    void metrix() throws FileNotFoundException, AWTException, InterruptedException {

        WebDriver ffdriver = new FirefoxDriver();
        PrintWriter fileWriter = new PrintWriter("Links.txt");
        Date date = new Date();
        fileWriter.println(date.toString());
        int error500 = 0;

        for (String i : XmlReader.urls) {

            ffdriver.get("http://gtmetrix.com/");
            currentLink = i;
            System.out.println("URL: " + i);

            WebElement urlTextBox = (new WebDriverWait(ffdriver, 60))
                    .ignoring(NoSuchElementException.class)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='analyze-url input-text']")));
            urlTextBox.clear();
            urlTextBox.sendKeys(i);

            ffdriver.findElement(By.xpath("//button[@value='Go!']")).click();


            WebElement inf;
            String report;

            try {
                inf = (new WebDriverWait(ffdriver, 60))
                        .ignoring(NoSuchElementException.class)
                        .pollingEvery(1, TimeUnit.SECONDS)
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("report-summary-details")));//className("report-summary-details")));
                //System.out.println(ffdriver.findElement(By.xpath("html/body/div[2]/div[1]/div[6]/div[3]/b[1]/following-sibling::text()[1]")).getText());
                System.out.println(inf.getText());
                report = ffdriver.getCurrentUrl();

                System.out.println("Report url: " + report);
                System.out.println();
                //fileWriter.println();
                //fileWriter.println(i + "," + report + "," + inf.getText());
                compareUrls.add(report);
            } catch (Exception e) {
                if (ffdriver.getCurrentUrl().contains("job_error")) {
                    System.out.println("ERROR 500: " + ffdriver.getCurrentUrl() + "\n");
                    fileWriter.println(i + "," + "500");
                    error500++;
                } else {
                    System.out.println(ffdriver.getCurrentUrl());
                }
            }

            ffdriver.get(ffdriver.getCurrentUrl());
        }

        XmlReader.urls.clear();
        XmlReader newXmlReader = new XmlReader();
        newXmlReader.XmlReader(XmlReader.live);
        System.out.println(XmlReader.urls.toString());
        System.out.println(compareUrls.toString());

        int j = 0;
        for (String i : compareUrls) {

            ffdriver.get(i);
            currentLink = XmlReader.urls.get(j);

            WebElement compareUrlLink = (new WebDriverWait(ffdriver, 60))
                    .ignoring(NoSuchElementException.class)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[2]/div[2]/div[2]/ul/li[2]/a")));
            compareUrlLink.click();

            WebElement anotherUrlBox = (new WebDriverWait(ffdriver, 60))
                    .ignoring(NoSuchElementException.class)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='compare-url']")));
            anotherUrlBox.sendKeys(XmlReader.urls.get(j));
            ffdriver.findElement(By.xpath(".//*[@id='compare-modal']/form/button[1]")).click();

            new WebDriverWait(ffdriver, 60).ignoring(NoSuchElementException.class).pollingEvery(1, TimeUnit.SECONDS)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[2]/div[1]/div[1]/h1")));

            String a1 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[1]/label[1]")).getText();
            String a2 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[1]/label[2]")).getText();
            String a3 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[1]/label[3]")).getText();
            String a4 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[1]/label[4]")).getText();
            String a5 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[1]/label[5]")).getText();

            String b1 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/span[1]")).getText();
            String b2 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/span[2]")).getText();
            String b3 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/span[1]")).getText();
            String b4 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/span[2]")).getText();
            String b5 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/span[3]")).getText();

            String c1 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[3]/div/span[1]")).getText();
            String c2 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[3]/div/span[2]")).getText();
            String c3 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[3]/span[1]")).getText();
            String c4 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[3]/span[3]")).getText();
            String c5 = ffdriver.findElement(By.xpath("html/body/div[2]/div[2]/div[3]/span[5]")).getText();

            System.out.println(ffdriver.getCurrentUrl());
            fileWriter.println(currentLink);
            fileWriter.println(ffdriver.getCurrentUrl());
            fileWriter.println(a1 + ","  + b1 + "," + c1);
            fileWriter.println(a2 + ","  + b2 + "," + c2);
            fileWriter.println(a3 + ","  + b3 + "," + c3);
            fileWriter.println(a4 + ","  + b4 + "," + c4);
            fileWriter.println(a5 + ","  + b5 + "," + c5 + "\n");

            j++;
        }

        System.out.println("500: " + error500);
        System.out.println("-------------------------");
        System.out.println("[REPORT HAS BEEN UPDATED]");
        System.out.println("-------------------------");
        System.out.println("\n" + date.toString());

        fileWriter.close();

        if (ffdriver != null) {
            ffdriver.close();
            ffdriver.quit();
        }
    }
}
