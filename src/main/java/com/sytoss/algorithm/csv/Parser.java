package com.sytoss.algorithm.csv;

import com.sytoss.algorithm.csv.readers.FileContent;
import com.sytoss.algorithm.csv.savers.CSVSaver;
import com.sytoss.algorithm.csv.savers.ISaver;
import com.sytoss.algorithm.csv.savers.JDOMSaver;
import com.sytoss.algorithm.csv.savers.SaxSaver;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class Parser {

    ISaver saver;

    //JDOM | Sax
    public Parser(FileContent fc, String filePath) throws XmlPullParserException, IOException {

        String fileType = filePath.substring(filePath.indexOf(".") + 1);

        if(fileType.equals("csv")) {

            saver = new CSVSaver(fc, filePath);

        }
        else if(fileType.equals("xml")) {

            if(fc.getLines().size() < 20) {
                saver = new JDOMSaver(fc, filePath);
            }
            else {
                saver = new SaxSaver(fc, filePath);
            }
        }

    }

    public static void main(String[] args) {

        try {
            if (args.length != 2) {
                throw new IllegalArgumentException();
            }
            new Parser(new FileContent(args[0]), args[1]);
        }
        catch(Exception e){
            help(e);
        }


    }

    private static void help(Exception exp) {
        System.out.println(exp.toString());
    }
}
