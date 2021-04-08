package com.sytoss.algorithm.csv;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

public class FileContentTest {

    private static final String CSV_SOURCE = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\list.csv";
    private static final String XML_SOURCE = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\list.xml";
    private static final String CSV_OUTPUT = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\outputToCSV.csv";


    @Test
    public void getLinesFromCSV() throws FileNotFoundException {

        FileContent fileContent = new FileContent(CSV_SOURCE);
        List<Line> persons = fileContent.getLines();

        for (Line line: persons) {
            PersonLine person = (PersonLine) line;

            for (int i = 0; i < line.cells.size(); i++) {
                assertFalse(line.cells.get(i).isEmpty());
            }
        }
    }

    @Test
    public void fromCSVtoXMUsingJSDOM() {

    }

    @Test
    public void fromCSVtoXMUsingSAX() {

    }

    @Test
    public void fromXMLtoCSV() throws FileNotFoundException, ParseException {

        FileContent fileContent1 = new FileContent(XML_SOURCE);
        List<Line> linesOriginal = fileContent1.getLines();
        Parser parser = new Parser(fileContent1, CSV_OUTPUT);

        FileContent fileContent2 = new FileContent(CSV_OUTPUT);
        List<Line> linesFromSavedFile = fileContent2.getLines();

        assertEquals(linesOriginal.size(), linesFromSavedFile.size());

        //persons count
        for (int i = 0; i < linesFromSavedFile.size(); i++) {

            //cells separately
            for (int j = 0; j < linesFromSavedFile.get(i).cells.size(); j++) {
                String original = linesOriginal.get(i).cells.get(j);
                String fromFile = linesFromSavedFile.get(i).cells.get(j);

                assertTrue(original.equals(fromFile));
            }

        }
    }

}