package com.sytoss.algorithm.csv.readers;

import com.sytoss.algorithm.csv.lines.Line;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface IReader {

    List<Line> read(String filePath) throws IOException, SAXException, ParserConfigurationException;

    default List<Line> validateLines(List<Line> lines) {
        for (Line line : lines) {
            line.validate();

        }

        return lines;
    }
}
