package com.sytoss.algorithm.csv.readers;

import com.sytoss.algorithm.csv.lines.Line;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public abstract class Reader {

    public abstract List<Line> read(String filePath) throws IOException, SAXException, ParserConfigurationException;

    public List<Line> transformLines(List<Line> lines) {
        for (Line line : lines) {
            line.transformToValid();

        }
        

        return lines;
    }
}
