package com.sytoss.algorithm.csv;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LineTest {

    private final static String PATH = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\test.csv";
    private final static String LIST_PATH = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\list.csv";
    //private final static String XML_PATH = "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\list.xml";

    @Test
    public void newLine() {
        String str = "1,Dani,Grechishkin,8";

        Line line = new Line(str);
        List<String> example = new ArrayList<>();
        Collections.addAll(example, str.split(","));

        assertEquals(example, line.getCells());
    }

    @Test
    public void personLine() {
        String person = "1,Jenya,Vasiliev,\"22.03.1995\",\"Our \"\"mentor\"\".\"";
        PersonLine line = new PersonLine(person);

        List<String> example1 = new ArrayList<>();
        Collections.addAll(example1, person.split(","));

        List<String> example2 = new ArrayList<>();
        example2.add("1");
        example2.add("Jenya");
        example2.add("Vasiliev");
        example2.add("22.03.1995");
        example2.add("Our \"mentor\".");

        assertEquals(example1, line.cells);
        line.validate();

        for (int i = 0; i < line.cells.size(); i++) {
            assertEquals(line.cells.get(i), example2.get(i));
        }
    }

    @Test
    public void compareNonValidInformation() throws FileNotFoundException {
        FileContent content = new FileContent(PATH);
        String[][] example = new String[][]{{"da", "da", "net"}, {"not", "yep", "da"}};

        for (int i = 0; i < content.getLines().size(); i++) {
            for (int j = 0; j < content.getLines().get(i).cells.size(); j++) {

                assertEquals(content.getLines().get(i).getCells().get(j), example[i][j]);

            }

        }

    }

    @Test
    public void compareValidInformation() throws FileNotFoundException {

        List<Line> list = new FileContent(LIST_PATH).getLines();
        String[][] expected = new String[][] {{"1","Jenya", "Vasiliev", "22.03.1995", "Our \"mentor\"."}};

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < list.get(i).getCells().size(); j++) {

                assertEquals(list.get(i).getCells().get(j), expected[i][j]);

            }

        }

    }

}