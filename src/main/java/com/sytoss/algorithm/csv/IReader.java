package com.sytoss.algorithm.csv;

import java.io.File;
import java.util.List;

public interface IReader {

    List<Line> read(String filePath);

    default List<Line> validateLines(List<Line> lines) {
        for (Line line : lines) {
            line.validate();

        }

        return lines;
    }
}
