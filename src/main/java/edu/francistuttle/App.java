package edu.francistuttle;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class App 
{
    public static void main( String[] args )
    {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response1 = client.execute(httpGet)) {
            final HttpEntity entity = response1.getEntity();
            if (entity != null) {
                try (InputStream inputStream = entity.getContent()) {
			    // pass inputStream to builder.parse()
                    


                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Hosed: " + e.toString());
        }


        try
        {
            File path = new File("C:\\Users\\ap1101037\\Desktop\\github-tran\\rssfeed\\src\\nasa.xml");

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader("https://www.nasa.gov/rss/dyn/breaking_news.rss"));

            System.out.println("Feed Title: " + feed.getTitle());
            System.out.println("Feed Link: " + feed.getLink());
            System.out.println("Feed Description: " + feed.getDescription());

            for(SyndEntry entry : (List<SyndEntry>) feed.getEntries())
            {
                System.out.println("\tEntry Title: " + entry.getTitle());
                System.out.println("\tEntry Link: " + entry.getLink());
                System.out.println("\tEntry Description: " + entry.getDescription());
            }
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}