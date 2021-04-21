package com.sytoss.algorithm.csv;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.readers.FileContent;
import com.sytoss.algorithm.csv.writer.CSVWriter;
import com.sytoss.algorithm.csv.writer.IWriter;
import com.sytoss.algorithm.csv.writer.JdomWriter;
import com.sytoss.algorithm.csv.writer.SaxWriter;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;


public class Parser {

    private IWriter saver;

    //JDOM | Sax
    public Parser(List<Line> lines, String filePath) throws IOException {

        String fileType = filePath.substring(filePath.indexOf(".") + 1);

        if(fileType.equals("csv")) {

            saver = new CSVWriter(lines, filePath);

        }
        else if(fileType.equals("xml")) {

            if(lines.size() < 20) {
                saver = new JdomWriter(lines, filePath);
            }
            else {
                saver = new SaxWriter(lines, filePath);
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
        System.out.println(exp.toString());
    }
}
