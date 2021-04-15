package com.sytoss.algorithm.csv;

import java.text.ParseException;


public class Parser {

    ISaver saver;

    //JDOM | Sax
    public Parser(FileContent fc, String filePath) {

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
