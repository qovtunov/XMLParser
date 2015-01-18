public  class Main {

    public static void main(String[] args) throws Exception  {

        //Driver.launchDriver();
        XmlReader newXmlReader = new XmlReader();
        newXmlReader.XmlReader(XmlReader.live);
        //PageNotFoundCheck newCheck = new PageNotFoundCheck();
        //newCheck.pageNotFoundCheck();
        //newXmlReader.readFromFile();
        GTmetrix newMetrix = new GTmetrix();
        newMetrix.metrix();
        //PageSpeedGoogle speed = new PageSpeedGoogle();
        //speed.replaceSubString();

        /*URL url = new URL("http://stackoverflow.com");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                System.out.println(line);
            }
        }*/

        /*String url = "http://stackoverflow.com/questions/3152138";
        Document document = Jsoup.connect(url).get();

        Element question = document.select("#question .post-text p").first();
        System.out.println("Question: " + question.text());

        Elements answerers = document.select("#answers .user-details a");
        for (Element answerer : answerers) {
            System.out.println("Answerer: " + answerer.text());
        }
*/

    }
}