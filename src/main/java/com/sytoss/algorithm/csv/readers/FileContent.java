package com.sytoss.algorithm.csv.readers;

import com.sytoss.algorithm.csv.lines.Line;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class FileContent {


    private Reader reader;

    private Reader getReaderType(String filePath) {
      String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);

      if(fileType.equals("csv")) {
          return new CSVReader();
      }
      else if(fileType.equals("xml")) {
          return new XMLReader();
      }
      else {
          System.out.println("FileContent: Error while reading following type< please use csv or xml: \n\t " + fileType + " is not allowed.");
          return null;
      }
  }

    public List<Line> getLines(String filePath) throws ParserConfigurationException, SAXException, IOException {
        reader = getReaderType(filePath);
        List<Line> lines = reader.read(filePath);
        return Collections.unmodifiableList(lines);
    }


}
