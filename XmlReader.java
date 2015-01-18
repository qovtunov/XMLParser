import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public class XmlReader {

    static String live = "live.xml";
    static String stag = "stage.xml";

    static List<String> urls = new ArrayList<>();

    int prodMob = 0;
    int stageMob = 0;


    void XmlReader(String file) throws FileNotFoundException {


        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(file));

            NodeList listOfUrls = doc.getElementsByTagName("url");
            int totalUrls = listOfUrls.getLength();
            System.out.println("Total URLs: " + totalUrls);
            System.out.println("-START PARSING FILE-");

            for (int i = 0; i < listOfUrls.getLength(); i++) {
                Node firstNode = listOfUrls.item(i);
                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element firstElement = (Element) firstNode;

                    NodeList firstList = firstElement.getElementsByTagName("loc");
                    Element firstUrlElement = (Element) firstList.item(0);

                    NodeList textFNList = firstUrlElement.getChildNodes();
                    String s = (textFNList.item(0)).getNodeValue().trim();

                    StringBuffer sb = new StringBuffer(s);

                    if (s.equals("http://appery.io/")) {
                        sb.replace(0, 17, "http://appery.io/home/");
                        prodMob = 1;
                    } else if (s.equals("http://stage-dev.appery.io/")) {
                        sb.replace(0, 27, "http://stage-dev.appery.io/home/");
                        stageMob = 1;
                    }

                    // comment if Desktop
                    /*sb = sb.deleteCharAt(sb.length() - 1);
                    if (prodMob == 1) {
                        sb.insert(17, "mobile/?"); //17 27
                    } else if (stageMob == 1) {
                        sb.insert(27, "mobile/?");
                    }*/
                    //

                    String stopLink = "http://appery.io/case-studies/attachment/majoruscitywater-case-study/";
                    if (s.equals(stopLink)) {
                        break;
                    } else {
                        //urls.add(s);
                        urls.add(sb.toString());
                    }

                    //System.out.println("Parsed: " + i + ". " + s);
                    System.out.println("Parsed: " + i + ". " + sb);
                }
            }
        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());

        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();

        } catch (Throwable t) {
            t.printStackTrace();
        }

        System.out.println("-PARSING FINISHED-\n");
    }

    void readFromFile() throws IOException {

        Stream<String> lines = Files.lines(Paths.get("stageLinks.txt"));
        //lines.forEach(s -> System.out.println(s));
        lines.forEach(s -> urls.add(s));
        //System.out.println(urls);
        lines.close();
    }
}