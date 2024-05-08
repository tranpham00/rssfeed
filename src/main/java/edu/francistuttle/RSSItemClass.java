package edu.francistuttle;

public class RSSItemClass {
    public String rssTitle;
    public String rssLink;
    public String rssImageLink;
    public String rssDescription;
    public String rssPubDate;

    public RSSItemClass(String t, String l, String iL, String d, String pD)
    {
        rssTitle = t;
        rssLink = l;
        rssImageLink = iL;
        rssDescription = d;
        rssPubDate = pD;
    }
}
