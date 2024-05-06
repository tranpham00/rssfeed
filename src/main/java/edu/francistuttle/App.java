package edu.francistuttle;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class App 
{
    public static void main( String[] args ) throws Exception
    {   
        Document document = readXMLDocumentFromFile("C:\\Users\\ap1101037\\Desktop\\github-tran\\rssfeed\\src\\main\\java\\edu\\francistuttle\\nasa.xml");

        //Verify XML Content

        //Here comes the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        //Get all channels
        NodeList nList = document.getElementsByTagName("item");
        System.out.println("============================");


        for (int temp = 0; temp < nList.getLength(); temp++) 
        {
        Node node = nList.item(temp);
    
            if (node.getNodeType() == Node.ELEMENT_NODE) 
            {
                //Print each computer's detail
                Element eElement = (Element) node;
                System.out.println("\nTitle : " + eElement.getElementsByTagName("title"));
                System.out.println("Link : " + eElement.getElementsByTagName("link").item(0).getTextContent());
                System.out.println("Publish Date : " + eElement.getElementsByTagName("pubDate").item(0).getTextContent());

                //NodeList cList = eElement.getElementsByTagName("item");
            }
        }


        // try
        // {
        //     File path = new File("C:\\Users\\ap1101037\\Desktop\\github-tran\\rssfeed\\src\\nasa.xml");

        //     SyndFeedInput input = new SyndFeedInput();
        //     SyndFeed feed = input.build(new XmlReader(path));

        //     System.out.println("Feed Title: " + feed.getTitle());
        //     System.out.println("Feed Link: " + feed.getLink());
        //     System.out.println("Feed Description: " + feed.getDescription());

        //     for(SyndEntry entry : (List<SyndEntry>) feed.getEntries())
        //     {
        //         System.out.println("\tEntry Title: " + entry.getTitle());
        //         System.out.println("\tEntry Link: " + entry.getLink());
        //         System.out.println("\tEntry Description: " + entry.getDescription());
        //     }

        // }
        // catch(Exception e)
        // {
        //     System.out.println("Error: " + e.getMessage());
        // }
    }

    public static Document readXMLDocumentFromFile(String fileNameWithPath) throws Exception 
    {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.nasa.gov/rss/dyn/breaking_news.rss");
        try (CloseableHttpResponse response1 = client.execute(httpGet)) {
            final HttpEntity entity = response1.getEntity();
            if (entity != null) {
                try (InputStream inputStream = entity.getContent()) {
                    // pass inputStream to builder.parse()
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();

                    Document document = builder.parse(inputStream);

                    document.getDocumentElement().normalize();
                    return document;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Hosed: " + e.toString());
        }
        return null;
    }
}

