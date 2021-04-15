package com.sytoss.algorithm.csv;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class FileContent {

    private List<Line> lines;
    private Reader reader;

    public FileContent(String filePath) {

        String fileType = filePath.substring(filePath.indexOf(".") + 1);


        if(fileType.equals("csv"))
            reader = new CSVReader();

        else if(fileType.equals("xml"))
            reader = new XMLReader();

        lines = reader.read(filePath);

    }

    /*
    private void readXMLToSAX(String filePath) {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(filePath));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    System.out.println("Start document");
                } else if (eventType == XmlPullParser.END_DOCUMENT) {
                    System.out.println("End document");
                } else if (eventType == XmlPullParser.START_TAG) {
                    System.out.println("Start tag " + xpp.getName());
                } else if (eventType == XmlPullParser.END_TAG) {
                    System.out.println("End tag " + xpp.getName());
                } else if (eventType == XmlPullParser.TEXT) {
                    System.out.println("Text " + xpp.getText());
                }
                eventType = xpp.next();
            }
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }*/


    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }


}
