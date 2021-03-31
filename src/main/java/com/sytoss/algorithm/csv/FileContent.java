package com.sytoss.algorithm.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileContent {
    private List<PersonLine> lines;

    public FileContent(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        lines = new ArrayList<PersonLine>();

        while (sc.hasNextLine()) {
            lines.add( new PersonLine(sc.nextLine()));
        }


    }

    public List<PersonLine> getLines() {
        return lines;
    }
}
