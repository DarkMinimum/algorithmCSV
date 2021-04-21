package com.sytoss.algorithm.csv.readers;
import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class SaxReader extends DefaultHandler {


    private List<Line> persons = new ArrayList<>();
    private StringBuilder person;
    private String lastElementName;

    @Override
    public void startDocument() {
        // Тут будет логика реакции на начало документа
    }

    @Override
    public void endDocument() {
        // Тут будет логика реакции на конец документа
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

            //System.out.println(lastElementName + " " + information);
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
        // Тут будет логика реакции на конец элемента
        if (person != null && qName.equals("Person")) {
            System.out.println(person.toString());
            persons.add(new PersonLine(person.toString()));

        }


    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) {
        // Тут будет логика реакции на пустое пространство внутри элементов (пробелы, переносы строчек и так далее).
    }

    public List<Line> getResultList() {
        return this.persons;
    }
}
