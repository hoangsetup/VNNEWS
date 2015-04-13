package com.fithou.vnnews.utils;

import java.io.StringReader;
import java.util.Vector;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.fithou.vnnews.models.NewsItem;

public class XMLPullGetNewsItems {
	private NewsItem item;
	private String string;

	public Vector<NewsItem> parseXMLGetNews(String XML, Vector<NewsItem> items) {
		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			parser = factory.newPullParser();
			// URL url = new URL(linkXML);
			// URLConnection connection = url.openConnection();
			parser.setInput(new StringReader(XML));
			String tagname = "";
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					tagname = parser.getName();
					if (tagname.equalsIgnoreCase("news"))
						item = new NewsItem();
					break;

				case XmlPullParser.TEXT:
					string = parser.getText();
					break;

				case XmlPullParser.END_TAG:
					tagname = parser.getName();
					// Log.d("XML_END TAG", tagname+"-------");
					if (tagname.equalsIgnoreCase("news")) {
						// add employee object to list
						items.add(item);

					} else if (tagname.equalsIgnoreCase("Id")) {
						item.setId(string);
					} else if (tagname.equalsIgnoreCase("Title")) {
						item.setTitle(string);
					} else if (tagname.equalsIgnoreCase("Link")) {
						// String t = string.split("/")[2];
						item.setNguon(string);
					} else if (tagname.equalsIgnoreCase("Image")) {
						item.setLnk_img(string.trim());
					} else if (tagname.equalsIgnoreCase("PublishedDate")) {
						item.setPubdate(string);
					}
					break;

				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return items;
	}
}
