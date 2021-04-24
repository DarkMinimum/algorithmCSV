package com.sytoss.algorithm.csv.readers;

import com.sytoss.algorithm.csv.lines.Line;
import com.sytoss.algorithm.csv.lines.PersonLine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader extends Reader {

    @Override
    public List<Line> read(String filePath) {

        File file = new File(filePath);
        Scanner sc = null;
        List<Line> lines = new ArrayList<>();

        try {
            sc = new Scanner(file);
        } catch (Exception exception) {
            System.out.println(exception);
        }

        while (sc.hasNextLine())
            lines.add(new PersonLine(sc.nextLine()));

        lines = transformLines(lines);
        sc.close();

        return lines;
    }


}
