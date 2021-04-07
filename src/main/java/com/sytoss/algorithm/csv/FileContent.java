package com.sytoss.algorithm.csv;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.StAXEventBuilder;
import org.xml.sax.SAXException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class FileContent {

    private List<Line> lines  = new ArrayList<>();

    public FileContent(String filePath) throws FileNotFoundException {

        String fileType = filePath.substring(filePath.indexOf(".") + 1);
        File file = new File(filePath);

        if(fileType.equals("csv")) {

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
                lines.add(new PersonLine(sc.nextLine()));

            validateLines();
            sc.close();
        }
        else if(fileType.equals("xml")) {

            Document document = null;

            double bytes = file.length();
            bytes = bytes / 1024;

            document = (bytes < 20d) ?  readXMLToJDOM(filePath) : readXMLToSAXj(filePath);
            /*if(bytes > 20d) {
                Document document = readXMLToJDOM(filePath);
                fromDocumentToLines(document);
            }
            else {
                readXMLToSAX(filePath);
            }*/
            fromDocumentToLines(document);
        }

    }

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
    }

    private Document readXMLToSAXj(String filePath) {

        Document result = null;

        SAXBuilder builder = new SAXBuilder();
        try
        {
            result = builder.build(filePath);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }
    private Document readXMLToJDOM(String filePath) {

        Document result = null;

        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            org.w3c.dom.Document w3cDocument = documentBuilder.parse(filePath);
            result = new DOMBuilder().build(w3cDocument);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }
    private void fromDocumentToLines(Document document) {
        ///from document to array
        Element rootNode = document.getRootElement();
        List<Element> tree = rootNode.getChildren("Person");

        for (Element element: tree) {

            String rawDate = element.getChildText("date");
            String date = rawDate.substring(rawDate.lastIndexOf("-")+1) + "." + rawDate.substring(rawDate.indexOf("-")+1, rawDate.lastIndexOf("-")) + "." + rawDate.substring(0,4);

            String person = element.getAttributeValue("id") + "," +
                    element.getChildText("name") + "," +
                    element.getChildText("surname") + "," +
                    date + "," +
                    element.getChildText("desc");

            lines.add(new PersonLine(person));
        }
    }
    private void validateLines() {
        for (int i = 0; i < getLines().size(); i++) {
            getLines().get(i).validate();

        }
    }
    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }


}
