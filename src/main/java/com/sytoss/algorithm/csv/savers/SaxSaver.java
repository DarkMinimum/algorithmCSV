package com.sytoss.algorithm.csv.savers;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;
import com.sytoss.algorithm.csv.readers.FileContent;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.*;

public class SaxSaver implements ISaver  {

    public SaxSaver(FileContent fc, String filePath) throws XmlPullParserException, IOException {
        write (fc, filePath);
    }

    @Override
    public void write(FileContent fc, String filePath) throws XmlPullParserException, IOException {

        //....

        /*
        XmlSerializer serial = new FastXmlSerializer();
        serial.setOutput(out,"utf-8");
        serial.startDocument(null,true);
        serial.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output",true);
        writeMapXml(val,null,serializer);
        serializer.endDocument();

         catch (Exception ex) {
            System.out.println("Could not generate public.xml file " + ex);
        }*/
    }

}
