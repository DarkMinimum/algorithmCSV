package com.sytoss.algorithm.csv.readers;

import com.sytoss.algorithm.csv.lines.Line;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;

import java.util.List;



public class XMLReader extends Reader {

    @Override
    public List<Line> read(String filePath) throws SAXException, ParserConfigurationException, IOException {

        File file = new File(filePath);

        double bytes = file.length();
        bytes = bytes / 1024;


        if(bytes < 20d) {

            return new JDOMReader().readXMLToJDOM(filePath);
        }
        else {

            return new SaxReader().readXMLToSAX(filePath);
        }

    }

}
