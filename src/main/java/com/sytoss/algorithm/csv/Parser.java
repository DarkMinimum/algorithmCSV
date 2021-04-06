package com.sytoss.algorithm.csv;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Parser {

    private final static String LIST_PATH = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\list.csv";
    private final static String OUTPUT_XML = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\list.xml";

    public static List<Line> parse(FileContent fc) {

        return fc.getLines();
    }

    public Parser(FileContent fc, String xmlPath) throws ParseException {
        Document doc = new Document();
        doc.setRootElement(new Element("Persons"));
        for (Line line : fc.getLines()) {

            PersonLine l = (PersonLine)line;

            Element person = new Element("Person");
            person.setAttribute("id", l.getNumber());
            person.addContent((new Element("name").setText(l.getName())));
            person.addContent((new Element("surname").setText(l.getSurname())));
            person.addContent((new Element("date").setText(l.getDateXML())));
            person.addContent((new Element("desc").setText(l.getNote())));
            doc.getRootElement().addContent(person);
            
        }

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());


        try {
            xmlOutputter.output(doc, new FileOutputStream(xmlPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            new Parser(new FileContent(LIST_PATH), OUTPUT_XML);
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }

    }
}
