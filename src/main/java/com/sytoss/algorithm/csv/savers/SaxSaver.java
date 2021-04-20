package com.sytoss.algorithm.csv.savers;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;
import com.sytoss.algorithm.csv.readers.FileContent;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.*;
import java.text.ParseException;
import java.util.List;

public class SaxSaver implements ISaver  {

    public SaxSaver(FileContent fc, String filePath) throws IOException {
        write (fc, filePath);
    }

    @Override
    public void write(FileContent fc, String filePath) throws IOException {

        //....

        OutputStream out = new FileOutputStream(filePath);

        try {

            XmlSerializer serializer = new FastXmlSerializer();
            serializer.setOutput(out, "utf-8");
            serializer.startDocument(null, true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            writeListXml(fc.getLines(), null, serializer);
            serializer.endDocument();
        }
         catch (Exception ex) {
            System.out.println("Could not generate public.xml file " + ex);
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
        } else if (v instanceof Long) {
            typeStr = "long";
        } else if (v instanceof Float) {
            typeStr = "float";
        } else if (v instanceof Double) {
            typeStr = "double";
        } else if (v instanceof Boolean) {
            typeStr = "boolean";
        /*} else if (v instanceof byte[]) {
            writeByteArrayXml((byte[])v, name, out);
            return;
        } else if (v instanceof int[]) {
            writeIntArrayXml((int[])v, name, out);
            return;
        } else if (v instanceof Map) {
            writeMapXml((Map<?, ?>)v, name, out);
            return;*/
        } else if (v instanceof List) {
            writeListXml((List<?>)v, name, out);
            return;
        } else if(v instanceof PersonLine) {
            writePersonLine((PersonLine) v, name, out);
            return;
        } else if (v instanceof CharSequence) {
            // XXX This is to allow us to at least write something if
            // we encounter styled text...  but it means we will drop all
            // of the styling information. :(
            out.startTag(null, "string");
            if (name != null) {
                out.attribute(null, "name", name);
            }
            out.text(v.toString());
            out.endTag(null, "string");
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

        //when null
        if (person == null) {
            out.startTag(null, "null");
            out.endTag(null, "null");
            return;
        }

        //start person tag
        out.text( "  ");
        out.startTag(null, "Person");
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.attribute(null, "id", person.getNumber());

        //name
        out.text("\n    ");
        out.startTag(null, "name");
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.text(person.getName());
        out.endTag(null, "name");

        //surname
        out.text("    ");
        out.startTag(null, "surname");
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.text(person.getSurname());
        out.endTag(null, "surname");

        //date
        out.text("    ");
        out.startTag(null, "date");
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.text(person.getBirthdayXML());
        out.endTag(null, "date");

        //desc
        out.text("    ");
        out.startTag(null, "desc");
        if (name != null) {
            out.attribute(null, "name", name);
        }
        out.text(person.getNote());
        out.endTag(null, "desc");

        //end person tag
        out.text("  ");
        out.endTag(null, "Person");
    }

}


