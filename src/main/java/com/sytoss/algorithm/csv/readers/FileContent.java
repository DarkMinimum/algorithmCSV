package com.sytoss.algorithm.csv.readers;

import com.sytoss.algorithm.csv.lines.Line;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class FileContent {

    private List<Line> lines;
    private com.sytoss.algorithm.csv.readers.IReader IReader;

    public FileContent(String filePath) throws ParserConfigurationException, SAXException, IOException {

        String fileType = filePath.substring(filePath.indexOf(".") + 1);


        if(fileType.equals("csv"))
            IReader = new CSVReader();

        else if(fileType.equals("xml"))
            IReader = new XMLReader();

        lines = IReader.read(filePath);

    }

    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }


}
