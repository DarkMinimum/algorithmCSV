package com.sytoss.algorithm.csv;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.readers.FileContent;
import com.sytoss.algorithm.csv.writer.CSVWriter;
import com.sytoss.algorithm.csv.writer.IWriter;
import com.sytoss.algorithm.csv.writer.JDOMWriter;
import com.sytoss.algorithm.csv.writer.SAXWriter;

import java.io.IOException;
import java.util.List;


public class Parser {

    private IWriter writer;

    public Parser(List<Line> lines, String filePath) throws IOException {

        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);

        if(fileType.equals("csv")) {

            writer = new CSVWriter(lines, filePath);

        }
        else if(fileType.equals("xml")) {

            if(lines.size() < 20) {
                writer = new JDOMWriter(lines, filePath);
            }
            else {
                writer = new SAXWriter(lines, filePath);
            }
        }

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
