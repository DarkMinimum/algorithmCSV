package com.sytoss.algorithm.csv;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;

public class JDOMSaver implements Saver {

    public JDOMSaver(FileContent fc, String xmlPath) {
        write(fc, xmlPath);
    }

    @Override
    public void write(FileContent fc, String xmlPath) {


            try {
            Document doc = new Document();
            doc.setRootElement(new Element("Persons"));

            for (Line line : fc.getLines()) {

                PersonLine l = (PersonLine) line;

                Element person = new Element("Person");
                person.setAttribute("id", l.getNumber());
                person.addContent((new Element("name").setText(l.getName())));
                person.addContent((new Element("surname").setText(l.getSurname())));
                person.addContent((new Element("date").setText(l.getBirthdayXML())));
                person.addContent((new Element("desc").setText(l.getNote())));
                doc.getRootElement().addContent(person);

            }

            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(doc, new FileOutputStream(xmlPath));

            } catch (Exception e) {
                e.printStackTrace();
            }

    }

}
