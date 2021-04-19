package com.sytoss.algorithm.csv.savers;

import com.sytoss.algorithm.csv.readers.FileContent;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public interface ISaver {

    void write(FileContent fc, String path) throws XmlPullParserException, IOException;

}
