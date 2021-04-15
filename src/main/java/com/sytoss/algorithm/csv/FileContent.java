package com.sytoss.algorithm.csv;

import java.util.Collections;
import java.util.List;


public class FileContent {

    private List<Line> lines;
    private IReader IReader;

    public FileContent(String filePath) {

        String fileType = filePath.substring(filePath.indexOf(".") + 1);


        if(fileType.equals("csv"))
            IReader = new CSVIReader();

        else if(fileType.equals("xml"))
            IReader = new XMLIReader();

        lines = IReader.read(filePath);

    }

    /*
    private void readXMLToSAX(String filePath) {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(filePath));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    System.out.println("Start document");
                } else if (eventType == XmlPullParser.END_DOCUMENT) {
                    System.out.println("End document");
                } else if (eventType == XmlPullParser.START_TAG) {
                    System.out.println("Start tag " + xpp.getName());
                } else if (eventType == XmlPullParser.END_TAG) {
                    System.out.println("End tag " + xpp.getName());
                } else if (eventType == XmlPullParser.TEXT) {
                    System.out.println("Text " + xpp.getText());
                }
                eventType = xpp.next();
            }
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }*/


    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }


}
