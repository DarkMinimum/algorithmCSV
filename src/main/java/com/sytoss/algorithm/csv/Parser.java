package com.sytoss.algorithm.csv;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.readers.FileContent;
import com.sytoss.algorithm.csv.writers.CSVWriter;
import com.sytoss.algorithm.csv.writers.IWriter;
import com.sytoss.algorithm.csv.writers.JDOMWriter;
import com.sytoss.algorithm.csv.writers.SAXWriter;
import org.xmlpull.v1.XmlPullParserException;


import java.io.IOException;
import java.util.List;


public class Parser {

    private IWriter writer;
    private List<Line> lines;
    private String filePath;

    public Parser(List<Line> lines, String filePath) {

        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
        this.lines = lines;
        this.filePath = filePath;

        if(fileType.equals("csv")) {

            writer = new CSVWriter();


        }
        else if(fileType.equals("xml")) {

            if(lines.size() < 20) {
                writer = new JDOMWriter();

            }
            else {
                writer = new SAXWriter();

            }
        }
        else {
            System.out.println("Parser can't use following type of file. . . \n\t ???");
        }

    }

    public void write() throws IOException, XmlPullParserException {
        writer.write(lines, filePath);
    }

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException();
            }
            new Parser(new FileContent().getLines(args[0]), args[1]);
        }
        catch(Exception e){
            help(e);
        }


    }

    private static void help(Exception exp) {
        System.out.println("Wrong usage of the application:\n" + "\t" + exp.getMessage());
        System.out.println("\t\nUse -java <source> <target> \n\t source - is input xml/csv file \n\t target - is output xml/csv file");
        System.exit(-1);
    }
}
