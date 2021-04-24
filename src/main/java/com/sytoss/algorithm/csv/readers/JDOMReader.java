package com.sytoss.algorithm.csv.readers;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class JDOMReader {

    public List<Line> readXMLToJDOM(String filePath) {

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


        if(result != null) {
            return fromDocumentToLines(result);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    private List<Line> fromDocumentToLines(Document document) {

        List<Line> lines = new ArrayList<>();


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
