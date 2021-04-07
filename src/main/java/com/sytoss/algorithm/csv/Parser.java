package com.sytoss.algorithm.csv;

import java.io.FileNotFoundException;
import java.text.ParseException;


public class Parser {

    Saver saver;

    //JDOM | Sax
    public Parser(FileContent fc, String filePath) throws ParseException {

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

    public static void main(String[] args) throws FileNotFoundException {

        try {
            if (args.length != 2) {
                throw new IllegalArgumentException();
            }
            new Parser(new FileContent(args[1]), "D:\\DevEnv\\Compilers\\algorithmCSV\\src\\test\\resources\\testFromXMLtoXML.xml");
        }
        catch(Exception e){
            help(e);
        }


    }

    private static void help(Exception exp) {
        System.out.println(exp.toString());
    }
}
