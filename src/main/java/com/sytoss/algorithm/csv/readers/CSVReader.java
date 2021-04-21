package com.sytoss.algorithm.csv.readers;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader extends IReader {

    @Override
    public List<Line> read(String filePath) {

        File file = new File(filePath);
        Scanner sc = null;
        List<Line> lines = new ArrayList<>();

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine())
            lines.add(new PersonLine(sc.nextLine()));

        lines = validateLines(lines);
        sc.close();

        return lines;
    }


}
