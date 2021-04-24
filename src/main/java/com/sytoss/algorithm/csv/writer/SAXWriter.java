package com.sytoss.algorithm.csv.writer;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.*;
import java.text.ParseException;
import java.util.List;

public class SAXWriter implements IWriter {

    public SAXWriter(List<Line> lines, String filePath) throws IOException {
        write (lines, filePath);
    }

    @Override
    public void write(List<Line> lines, String filePath) throws IOException {



        OutputStream out = new FileOutputStream(filePath);

        try {

            XmlSerializer serializer = new FastXmlSerializer();
            serializer.setOutput(out, "utf-8");
            serializer.startDocument(null, true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            writeListXml(lines, null, serializer);
            serializer.endDocument();
        }
        catch (Exception exception) {
            System.out.println("Could not generate public.xml file:\n" + "\t" + exception);
        }
    }

    public static void writeValueXml(Object v, String name, XmlSerializer out)
            throws XmlPullParserException, java.io.IOException, ParseException {
        String typeStr;
        if (v == null) {
            out.startTag(null, "null");
            if (name != null) {
                out.attribute(null, "name", name);
            }
            out.endTag(null, "null");
            return;
        } else if (v instanceof String) {
            out.startTag(null, "string");
            if (name != null) {
                out.attribute(null, "name", name);
            }
            out.text(v.toString());
            out.endTag(null, "string");
            return;
        } else if (v instanceof Integer) {
            typeStr = "int";
        } else if (v instanceof List) {
            writeListXml((List<?>)v, name, out);
            return;
        } else if(v instanceof PersonLine) {
            writePersonLine((PersonLine) v, name, out);
            return;
        } else {
            throw new RuntimeException("writeValueXml: unable to write value " + v);
        }

        out.startTag(null, typeStr);
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.attribute(null, "value", v.toString());
        out.endTag(null, typeStr);
    }

    public static void writeListXml(List<?> val, String name, XmlSerializer out)
            throws XmlPullParserException, java.io.IOException, ParseException {
        if (val == null) {
            out.startTag(null, "null");
            out.endTag(null, "null");
            return;
        }

        out.startTag(null, "Persons");
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.text("\n");
        int N = val.size();
        int i=0;
        while (i < N) {
            writeValueXml(val.get(i), null, out);
            i++;
        }


        out.endTag(null, "Persons");
    }

    public static void writePersonLine(PersonLine person, String name, XmlSerializer out) throws IOException, ParseException {


        if (person == null) {
            out.startTag(null, "null");
            out.endTag(null, "null");
            return;
        }

        out.text( "  ");
        out.startTag(null, "Person");
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.attribute(null, "id", person.getNumber());

        out.text("\n    ");
        out.startTag(null, "name");
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.text(person.getName());
        out.endTag(null, "name");

        out.text("    ");
        out.startTag(null, "surname");
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.text(person.getSurname());
        out.endTag(null, "surname");

        out.text("    ");
        out.startTag(null, "date");
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.text(person.getBirthdayXML());
        out.endTag(null, "date");

        out.text("    ");
        out.startTag(null, "desc");
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.text(person.getNote());
        out.endTag(null, "desc");

        out.text("  ");
        out.endTag(null, "Person");
    }

}

