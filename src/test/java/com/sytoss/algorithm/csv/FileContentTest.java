package com.sytoss.algorithm.csv;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;
import com.sytoss.algorithm.csv.readers.FileContent;
import com.sytoss.algorithm.csv.savers.SaxSaver;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

public class FileContentTest {

    private static final String CSV_SOURCE = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\list.csv";
    private static final String CSV_OUTPUT = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\outputToCSV.csv";
    private static final String XML_SOURCE = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\list.xml";
    private static final String XML_OUTPUT = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\outputToXML.xml";
    private static final String XML_SOURCE_LARGE = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\longListForXML.xml";
    private static final String XML_SAX_OUTPUT = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\SaxSaverTest.xml";
    @Test
    public void getLinesFromCSV() throws IOException, SAXException, ParserConfigurationException {

        FileContent fileContent = new FileContent(CSV_SOURCE);
        List<Line> persons = fileContent.getLines();

        for (Line line: persons) {
            PersonLine person = (PersonLine) line;

            for (int i = 0; i < line.getCells().size(); i++) {
                assertFalse(line.getCells().get(i).isEmpty());
            }
        }
    }

    @Test
    public void fromCSVtoXMUsingJDOM() throws IOException, ParseException, SAXException, ParserConfigurationException, XmlPullParserException {
        FileContent fileContent1 = new FileContent(CSV_SOURCE);
        List<Line> linesOriginal = fileContent1.getLines();
        Parser parser = new Parser(fileContent1, XML_OUTPUT);

        FileContent fileContent2 = new FileContent(XML_OUTPUT);
        List<Line> linesFromSavedFile = fileContent2.getLines();

        //persons count
        for (int i = 0; i < linesFromSavedFile.size(); i++) {

            //getCells() separately
            for (int j = 0; j < linesFromSavedFile.get(i).getCells().size(); j++) {

                String original = linesOriginal.get(i).getCells().get(j);
                String fromFile = linesFromSavedFile.get(i).getCells().get(j);


                if(j == 3) {

                    assertTrue(((PersonLine)linesOriginal.get(i)).getBirthdayXML().equals(linesFromSavedFile.get(i).getCells().get(j)));
                    continue;
                }

                assertTrue(original.equals(fromFile));
            }

        }
    }

    @Test
    public void fromCSVtoXMUsingSAXj() throws IOException, SAXException, ParserConfigurationException {
        FileContent fileContent = new FileContent(XML_SOURCE_LARGE);

        List<Line> lines = fileContent.getLines();
        assertTrue(lines.size() >= 999);

    }

    @Test
    public void fromXMLtoCSV() throws IOException, ParseException, SAXException, ParserConfigurationException, XmlPullParserException {

        FileContent fileContent1 = new FileContent(XML_SOURCE);
        List<Line> linesOriginal = fileContent1.getLines();
        Parser parser = new Parser(fileContent1, CSV_OUTPUT);

        FileContent fileContent2 = new FileContent(CSV_OUTPUT);
        List<Line> linesFromSavedFile = fileContent2.getLines();

        assertEquals(linesOriginal.size(), linesFromSavedFile.size());

        //persons count
        for (int i = 0; i < linesFromSavedFile.size(); i++) {

            //getCells() separately
            for (int j = 0; j < linesFromSavedFile.get(i).getCells().size(); j++) {
                String original = linesOriginal.get(i).getCells().get(j);
                String fromFile = linesFromSavedFile.get(i).getCells().get(j);

                assertTrue(original.equals(fromFile));
            }

        }
    }

    @Test
    public void usingSaxSaver() throws IOException, SAXException, ParserConfigurationException, XmlPullParserException {
        FileContent fc = new FileContent(CSV_SOURCE);
        SaxSaver saver = new SaxSaver(fc, XML_SAX_OUTPUT);
    }

}