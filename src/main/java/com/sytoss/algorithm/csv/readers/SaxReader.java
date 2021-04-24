package com.sytoss.algorithm.csv.readers;
import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;
import org.xml.sax.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SaxReader extends DefaultHandler {


    private List<Line> persons = new ArrayList<>();
    private StringBuilder person;
    private String lastElementName;

    public List<Line> readXMLToSAX(String filePath) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File(filePath), this);

        return persons;
    }

    @Override
    public void startDocument() {

    }

    @Override
    public void endDocument() {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        lastElementName = qName;


        if (qName.equals("Person")) {

            person = new StringBuilder();
            person.append(attributes.getValue("id")).append(",");

        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String information = new String(ch, start, length);

        information = information.replace("\n", "").trim();

        if (!information.isEmpty()) {

            if (lastElementName.equals("name"))
                person.append(information).append(",");
            if (lastElementName.equals("surname"))
                person.append(information).append(",");
            if (lastElementName.equals("date"))
                person.append(information).append(",");
            if (lastElementName.equals("desc"))
                person.append(information);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (person != null && qName.equals("Person")) {
           persons.add(new PersonLine(person.toString()));

        }


    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) {

    }

}
