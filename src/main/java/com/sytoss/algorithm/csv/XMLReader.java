package com.sytoss.algorithm.csv;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReader implements Reader {

    @Override
    public List<Line> read(String filePath) {

        File file = new File(filePath);
        Document document;

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

        return fromDocumentToLines(document);
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
    private List<Line> fromDocumentToLines(Document document) {

        List<Line> lines = new ArrayList<>();

        ///from document to array
        Element rootNode = document.getRootElement();
        List<Element> tree = rootNode.getChildren("Person");

        for (Element element: tree) {

            String date = generateDate(element.getChildText("date"));

            String person = element.getAttributeValue("id") + "," +
                    element.getChildText("name") + "," +
                    element.getChildText("surname") + "," +
                    date + "," +
                    element.getChildText("desc");

            lines.add(new PersonLine(person));
        }

        return lines;
    }
    private String generateDate(String rawDate) {

        String rawMonth = rawDate.substring(rawDate.indexOf("-")+1, rawDate.lastIndexOf("-"));
        String month = (Integer.parseInt(rawMonth) < 10) ? "0" + rawMonth : rawMonth;

        String rawDay = rawDate.substring(rawDate.lastIndexOf("-")+1);
        String day = (Integer.parseInt(rawDay) < 10) ? "0" + rawDay : rawDay;

        return day + "." + month + "." + rawDate.substring(0,4);
    }


}
