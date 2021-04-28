package com.sytoss.algorithm.csv.writers;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.util.List;

public class JDOMWriter implements IWriter {



    @Override
    public void write(List<Line> lines, String xmlPath) {

        try {

            Document doc = new Document();
            doc.setRootElement(new Element("Persons"));

            for (Line line : lines) {

                PersonLine l = (PersonLine) line;

                Element person = new Element("Person");
                person.setAttribute("id", l.getNumber());

                Element name = new Element("FullName");
                name.addContent(new Element("name").setText(l.getName()));
                name.addContent(new Element("surname").setText(l.getSurname()));
                person.addContent(name);


                person.addContent((new Element("date").setText(l.getBirthdayXML())));
                person.addContent((new Element("desc").setText(l.getNote())));
                doc.getRootElement().addContent(person);

            }

            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(doc, new FileOutputStream(xmlPath));

            } catch (Exception exception) {
                System.out.println("JDOMWriter: Some errors occurred while writing using JdomWriter:\n" + "\t" + exception);
            }

    }

}
