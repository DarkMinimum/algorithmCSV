package com.sytoss.algorithm.csv.writer;

import com.sytoss.algorithm.csv.lines.Line;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public interface IWriter {

    void write(List<Line> lines, String path) throws XmlPullParserException, IOException;

}
