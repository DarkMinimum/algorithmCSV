package com.sytoss.algorithm.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class FileContent {
    private List<PersonLine> lines  = new ArrayList<>();

    public FileContent(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) lines.add(new PersonLine(sc.nextLine()));

        validateLines();
        sc.close();


    }

    private void validateLines() {
        for (int i = 0; i < getLines().size(); i++) {
            getLines().get(i).validate();

        }
    }


    public List<PersonLine> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
