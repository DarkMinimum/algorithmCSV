package com.sytoss.algorithm.csv;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;
import com.sytoss.algorithm.csv.readers.FileContent;
import com.sytoss.algorithm.csv.writer.SaxWriter;
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

        List<Line> persons = new FileContent().getLines(CSV_SOURCE);

        for (Line line: persons) {

            for (int i = 0; i < line.getCells().size(); i++) {
                assertFalse(line.getCells().get(i).isEmpty());
            }
        }
    }

    @Test
    public void fromCSVtoXMUsingJDOM() throws IOException, ParseException, SAXException, ParserConfigurationException {

        List<Line> linesOriginal = new FileContent().getLines(CSV_SOURCE);
        Parser parser = new Parser(linesOriginal, XML_OUTPUT);

        List<Line> linesFromSavedFile = new FileContent().getLines(XML_OUTPUT);

        //persons count
        for (int i = 0; i < linesFromSavedFile.size(); i++) {

            //getCells() separately
            for (int j = 0; j < linesFromSavedFile.get(i).getCells().size(); j++) {

                String original = linesOriginal.get(i).getCells().get(j);
                String fromFile = linesFromSavedFile.get(i).getCells().get(j);


                if(j == 3) {

                    assertEquals(((PersonLine) linesOriginal.get(i)).getBirthdayXML(), linesFromSavedFile.get(i).getCells().get(j));
                    continue;
                }

                assertEquals(original, fromFile);
            }

        }
    }

    @Test
    public void fromCSVtoXMUsingSAXj() throws IOException, SAXException, ParserConfigurationException {
        List<Line> lines = new FileContent().getLines(XML_SOURCE_LARGE);
        assertTrue(lines.size() >= 999);

    }

    @Test
    public void fromXMLtoCSV() throws IOException, SAXException, ParserConfigurationException {


        List<Line> linesOriginal = new FileContent().getLines(XML_SOURCE);
        Parser parser = new Parser(linesOriginal, CSV_OUTPUT);
        List<Line> linesFromSavedFile = new FileContent().getLines(CSV_OUTPUT);

        assertEquals(linesOriginal.size(), linesFromSavedFile.size());

        //persons count
        for (int i = 0; i < linesFromSavedFile.size(); i++) {

            //getCells() separately
            for (int j = 0; j < linesFromSavedFile.get(i).getCells().size(); j++) {
                String original = linesOriginal.get(i).getCells().get(j);
                String fromFile = linesFromSavedFile.get(i).getCells().get(j);

                assertEquals(original, fromFile);
            }

        }
    }

    @Test
    public void usingSaxSaver() throws IOException, SAXException, ParserConfigurationException {
        SaxWriter saver = new SaxWriter(new FileContent().getLines(CSV_SOURCE), XML_SAX_OUTPUT);
    }

}